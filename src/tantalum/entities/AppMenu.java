package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@javax.persistence.Table(name = "dd_menu")
public class AppMenu extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "databaseID")
	private AppDatabase database;

	public AppDatabase getDatabase() {
		return database;
	}

	public void setDatabase(AppDatabase database) {
		this.database = database;
	}

}
