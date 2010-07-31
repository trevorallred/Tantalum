package tantalum.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;

import tantalum.entities.Field;
import tantalum.entities.MetaIndex;
import tantalum.entities.MetaIndexColumn;
import tantalum.entities.View;
import tantalum.util.DbConnection;
import tantalum.util.InsertSQL;
import tantalum.util.Strings;
import tantalum.util.UpdateSQL;

public class DataSaver {
	private Connection conn = null;
	private boolean testing = false;

	private void open() throws NamingException, SQLException {
		if (testing) {
			conn = new MockConnection();
			return;
		}
		conn = DbConnection.getConnection();
	}

	public void save(View view, InstanceList list) throws NamingException,
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

	private void saveView(View view, InstanceList list) throws SQLException {
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
						insertData.put(field.getBasisColumn().getDbName(),
								instance.getString(field));
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
							updateSql.addField(field.getBasisColumn()
									.getDbName(), instance.getString(field));
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
			for (View childView : view.getChildViews()) {
				saveView(childView, instance
						.getViewContent(childView.getName()));
			}
		}
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}
}
