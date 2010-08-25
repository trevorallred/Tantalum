package tantalum.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private Map<Model, Store> stores = null;

	private void open() throws NamingException, SQLException {
		if (testing) {
			conn = new MockConnection();
			return;
		}
		conn = DbConnection.getConnection();
	}

	public void save(Model model, Map<Model, Store> stores) throws NamingException, SQLException {
		open();
		try {
			conn.setAutoCommit(false);
			this.stores = stores;
			saveModel(model);
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

	private void saveModel(Model model) throws SQLException {
		Store store = stores.get(model);
		if (store == null || store.isEmpty())
			return;

		Field primaryKeyField = model.getPrimaryKey();

		if (store.getDestroys().size() > 0) {
			// DELETE
			// TODO implement recursive cascading
			// for (Model childModel : model.getChildModels()) {
			// }
			StringBuilder idsToDelete = new StringBuilder();
			for (Record instance : store.getDestroys()) {
				String id = instance.getString(primaryKeyField.getName());
				if (idsToDelete.length() > 0)
					idsToDelete.append(",");
				idsToDelete.append("'");
				idsToDelete.append(Strings.escapeQuotes(id));
				idsToDelete.append("'");
			}
			String deleteSql = "DELETE FROM `" + model.getBasisTable().getDbName() + "` WHERE "
					+ primaryKeyField.getBasisColumn().getDbName() + " IN (" + idsToDelete.toString() + ")";
			Statement stmt = conn.createStatement();
			System.out.println("Running: " + deleteSql.toString());
			stmt.executeUpdate(deleteSql.toString());
			stmt.close();
		}
		if (store.getCreates().size() > 0) {
			// INSERT
			InsertSQL insertSql = new InsertSQL(model.getBasisTable().getDbName());
			List<Record> instancesToInsert = new ArrayList<Record>();
			for (Record instance : store.getCreates()) {
				Map<String, String> insertData = new HashMap<String, String>();
				for (Field field : model.getFields()) {
					if (field.getBasisColumn() != null) {
						String value = null;
						boolean saveField = true;
						if (field.getBasisColumn().getColumnType().isWho()) {
							switch (field.getBasisColumn().getColumnType()) {
							case CreatedBy:
							case UpdatedBy:
								if (currentUserID == null) {
									System.out.println("WARNING: Remember to setCurrentUserID() before calling save");
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
									System.out.println("WARNING: Remember to setCurrentProcess() before calling save");
									value = "UNKNOWN";
								} else {
									value = currentProcess;
								}
							}
						} else if (field.getBasisColumn().getColumnType() == ColumnType.AutoIncrement) {
							saveField = false;
						} else if (field.getBasisColumn().getColumnType() == ColumnType.UUID) {
							String oldValue = instance.getString(field.getName());
							value = UUID.randomUUID().toString();
							// Put the UUID back into the record so we can return it back to the client
							instance.setValue(field.getName(), value);
							if (!Strings.isEmpty(oldValue))
								store.getPhantomIDs().put(oldValue, value);
						} else {
							value = instance.getStringForDB(field);
						}
						if (saveField) {
							insertData.put(field.getBasisColumn().getDbName(), value);
						}
					}
				}
				insertSql.addRow(insertData);
				instancesToInsert.add(instance);
			}
			Statement stmt = conn.createStatement();
			System.out.println("Running: " + insertSql.toString());
			stmt.executeUpdate(insertSql.toString(), Statement.RETURN_GENERATED_KEYS);
			ResultSet keyRs = stmt.getGeneratedKeys();
			int i = 0;
			while (keyRs.next()) {
				instancesToInsert.get(i).setValue(primaryKeyField.getName(), keyRs.getString(1));
				i++;
			}
			stmt.close();
		}
		if (store.getUpdates().size() > 0) {
			for (Record instance : store.getUpdates()) {
				UpdateSQL updateSql = new UpdateSQL(model.getBasisTable().getDbName());
				for (Field field : model.getFields()) {
					if (field.getBasisColumn() != null) {
						if (field.getReference() == null && instance.getFieldNames().contains(field.getName())) {
							boolean primaryKey = false;
							for (MetaIndex index : field.getBasisColumn().getTable().getIndexes()) {
								if (index.isUniqueIndex()) {
									for (MetaIndexColumn indexColumn : index.getColumns()) {
										if (indexColumn.getColumn() == field.getBasisColumn())
											primaryKey = true;
									}
								}
							}
							if (primaryKey) {
								updateSql.addWhere(field.getBasisColumn().getDbName() + " = '"
										+ Strings.escapeQuotes(instance.getString(field)) + "'");
							} else if (field.isEditable()) {
								String value = null;
								boolean saveField = true;
								if (field.getBasisColumn().getColumnType().isWho()) {
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
									updateSql.addField(field.getBasisColumn().getDbName(), value);
								}
							}
						}
					}
				}
				Statement stmt = conn.createStatement();
				System.out.println("Running: " + updateSql.toString());
				stmt.executeUpdate(updateSql.toString());
				stmt.close();
			}
			for (Model childModel : model.getChildModels()) {
				saveModel(childModel);
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
