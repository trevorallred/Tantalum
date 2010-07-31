package tantalum.entities;

public enum SortDirection {
	Ascending("ASC"), Descending("DESC");
	private String abbreviation;

	private SortDirection(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
}
