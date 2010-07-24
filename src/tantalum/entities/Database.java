package tantalum.entities;

import tantalum.data.DatabaseType;

public class Database extends BaseTable {
	private DatabaseType type = DatabaseType.MySQL;
	private String server = "localhost";
	private String database = "tenfold";
	private String username = "tf_user";
	private String password = "tf_password";

	public DatabaseType getType() {
		return type;
	}

	public void setType(DatabaseType type) {
		this.type = type;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}