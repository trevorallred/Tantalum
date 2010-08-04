package tantalum.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;

import tantalum.entities.ColumnType;
import tantalum.entities.Field;
import tantalum.entities.MetaIndex;
import tantalum.entities.MetaIndexColumn;
import tantalum.entities.Model;
import tantalum.util.DbConnection;
import tantalum.util.InsertSQL;
import tantalum.util.Strings;
import tantalum.util.UpdateSQL;

public class DataSaver {
	private Connection conn = null;
	private boolean testing = false;
	private Date now = new Date();
	private Integer currentUserID = null;
	private String currentProcess = null;

	private void open() throws NamingException, SQLException {
		if (testing) {
			conn = new MockConnection();
			return;
		}
		conn = DbConnection.getConnection();
	}

	public void save(Model view, InstanceList list) throws NamingException,
			SQLException {
		open();
		try {
			conn.setAutoCommit(false);
			saveView(view, list);
			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Failed to do rollback");
			}
			throw e;
		} finally {
			if (conn == null)
				return;
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Failed to do close connection");
			}
		}
	}

	private void saveView(Model view, InstanceList list) throws SQLException {
		Field primaryKeyField = null;
		for (Field field : view.getFields()) {
			if (field.getReference() == null
					&& view.getBasisTable().getPrimaryKey() == field
							.getBasisColumn()) {
				primaryKeyField = field;
				break;
			}
		}
		if (primaryKeyField == null) {
			System.out.println("Failed to find a primaryKeyField for "
					+ view.getBasisTable() + " on " + view);
		}
		{
			Set<Integer> idsToDelete = new HashSet<Integer>();
			for (Instance instance : list.getData()) {
				if (instance.isDelete()) {
					idsToDelete.add(instance.getInteger(primaryKeyField
							.getName()));
					instance.setDirty(false);
				}
			}
			if (idsToDelete.size() > 0) {
				String deleteSql = "DELETE FROM "
						+ view.getBasisTable().getDbName() + " WHERE "
						+ primaryKeyField.getBasisColumn().getDbName()
						+ " IN (" + Strings.joinForDB(idsToDelete) + ")";
				Statement stmt = conn.createStatement();
				System.out.println("Running: " + deleteSql.toString());
				stmt.executeUpdate(deleteSql.toString());
				stmt.close();
			}
		}
		{
			InsertSQL insertSql = new InsertSQL(view.getBasisTable()
					.getDbName());
			List<Instance> instancesToInsert = new ArrayList<Instance>();
			for (Instance instance : list.getData()) {
				if (instance.isDirty() && !instance.isDelete()
						&& Strings.isEmpty(instance.getString(primaryKeyField))) {
					Map<String, String> insertData = new HashMap<String, String>();
					for (Field field : view.getFields()) {
						if (field.getBasisColumn() != null) {
							String value = null;
							boolean saveField = true;
							if (field.getDefaultField() != null) {
								// We may need to consider this:
								// field.getDefaultFieldType()
								value = instance.getParent().getString(
										field.getDefaultField());
							} else if (field.getBasisColumn().getColumnType()
									.isWho()) {
								switch (field.getBasisColumn().getColumnType()) {
								case CreatedBy:
								case UpdatedBy:
									if (currentUserID == null) {
										System.out
												.println("WARNING: Remember to setCurrentUserID() before calling save");
										value = null;
									} else {
										value = currentUserID.toString();
									}
									break;
								case CreationDate:
								case UpdateDate:
									value = Strings.formatDateTime(now);
									break;
								default:
									if (currentProcess == null) {
										System.out
												.println("WARNING: Remember to setCurrentProcess() before calling save");
										value = "UNKNOWN";
									} else {
										value = currentProcess;
									}
								}
							} else if (field.getBasisColumn().getColumnType() == ColumnType.AutoIncrement) {
								// TODO If using a sequence number, include it
								// here
								// don't bother including the AutoIncrement
								// columns
								saveField = false;
							} else if (field.getBasisColumn().getColumnType() == ColumnType.UUID) {
								value = UUID.randomUUID().toString();
							} else {
								value = instance.getStringForDB(field);
							}
							if (saveField) {
								insertData.put(field.getBasisColumn()
										.getDbName(), value);
							}
						}
					}
					insertSql.addRow(insertData);
					instancesToInsert.add(instance);
					instance.setDirty(false);
				}
			}
			if (instancesToInsert.size() > 0) {
				Statement stmt = conn.createStatement();
				System.out.println("Running: " + insertSql.toString());
				stmt.executeUpdate(insertSql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ResultSet keyRs = stmt.getGeneratedKeys();
				int i = 0;
				while (keyRs.next()) {
					instancesToInsert.get(i).setValue(
							primaryKeyField.getName(), keyRs.getString(1));
					i++;
				}
				stmt.close();
			}
		}
		for (Instance instance : list.getData()) {
			if (instance.isDirty()) {
				UpdateSQL updateSql = new UpdateSQL(view.getBasisTable()
						.getDbName());
				for (Field field : view.getFields()) {
					if (field.getBasisColumn() != null) {
						if (field.getReference() == null
								&& instance.getFieldNames().contains(
										field.getName())) {
							boolean primaryKey = false;
							for (MetaIndex index : field.getBasisColumn()
									.getTable().getIndexes()) {
								if (index.isUniqueIndex()) {
									for (MetaIndexColumn indexColumn : index
											.getColumns()) {
										if (indexColumn.getColumn() == field
												.getBasisColumn())
											primaryKey = true;
									}
								}
							}
							if (primaryKey) {
								updateSql.addWhere(field.getBasisColumn()
										.getDbName()
										+ " = " + instance.getString(field));
							} else if (field.isEditable()) {
								String value = null;
								boolean saveField = true;
								if (field.getDefaultField() != null && field.getDefaultFieldType().isHard()) {
									value = instance.getParent().getString(
											field.getDefaultField());
								} else if (field.getBasisColumn().getColumnType()
										.isWho()) {
									switch (field.getBasisColumn().getColumnType()) {
									case CreatedBy:
										saveField = false;
										break;
									case UpdatedBy:
										if (currentUserID == null) {
											System.out
													.println("WARNING: Remember to setCurrentUserID() before calling save");
											value = null;
										} else {
											value = currentUserID.toString();
										}
										break;
									case CreationDate:
										saveField = false;
										break;
									case UpdateDate:
										value = Strings.formatDateTime(now);
										break;
									default:
										if (currentProcess == null) {
											System.out
													.println("WARNING: Remember to setCurrentProcess() before calling save");
											value = "UNKNOWN";
										} else {
											value = currentProcess;
										}
									}
								} else if (field.getBasisColumn().getColumnType() == ColumnType.AutoIncrement) {
									saveField = false;
								} else {
									value = instance.getStringForDB(field);
								}
								if (saveField) {
									updateSql
									.addField(field.getBasisColumn()
											.getDbName(), value);
								}
							}
						}
					}
				}
				if (updateSql.getFieldSize() > 0
						&& updateSql.getWhereSize() > 0) {
					Statement stmt = conn.createStatement();
					System.out.println("Running: " + updateSql.toString());
					stmt.executeUpdate(updateSql.toString());
					stmt.close();
				}
			}
			for (Model childView : view.getChildModels()) {
				saveView(childView, instance
						.getViewContent(childView.getName()));
			}
		}
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

}
