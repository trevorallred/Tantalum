package tantalum.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import tantalum.DatabaseException;
import tantalum.NoResultFoundException;
import tantalum.data.Instance;
import tantalum.data.InstanceUtility;


public class DbConnection {

	private int rowCount = 0;

	public static Connection getConnection() throws NamingException,
			SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/tantalumDB");
		return ds.getConnection();
	}

	public List<Instance> select(SelectSQL sql) throws DatabaseException {
		return select(sql.toString(), false);
	}

	public List<Instance> select(String sql) throws DatabaseException {
		return select(sql, false);
	}

	public List<Instance> select(String sql, boolean countRows)
			throws DatabaseException {
		Connection conn = null;
		Statement stmt = null; // Or PreparedStatement if needed
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			List<Instance> list = InstanceUtility.parseResultSet(rs);
			rs.close();
			rs = null;

			if (countRows) {
				ResultSet tempRS = stmt.executeQuery("SELECT FOUND_ROWS()");
				tempRS.next();
				rowCount = tempRS.getInt(1);
				tempRS.close();
			}

			stmt.close();
			stmt = null;
			conn.close(); // Return to connection pool
			conn = null; // Make sure we don't close it twice
			return list;
		} catch (NamingException e) {
			throw new DatabaseException(e);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					;
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					;
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					;
				}
				conn = null;
			}
		}
	}

	public Instance selectSingle(SelectSQL sql)
			throws NoResultFoundException {
		return selectSingle(sql.toString());
	}

	public Instance selectSingle(String sql)
			throws NoResultFoundException {
		List<Instance> result = select(sql);
		if (result.size() == 0)
			throw new NoResultFoundException();
		return result.get(0);
	}

	public int getRowCount() {
		return rowCount;
	}

	public int execute(UpdateSQL sql) {
		int changedRows = 0;
		Connection conn = null;
		Statement stmt = null; // Or PreparedStatement if needed
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			changedRows = stmt.executeUpdate(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);
			stmt.close();
			stmt = null;
			conn.close(); // Return to connection pool
			conn = null; // Make sure we don't close it twice
		} catch (NamingException e) {
			throw new DatabaseException();
		} catch (SQLException e) {
			throw new DatabaseException();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					;
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					;
				}
				conn = null;
			}
		}
		return changedRows;
	}

}
