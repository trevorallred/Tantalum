package tantalum.entities;

public enum ColumnType {
	String, Date, DateTime, Integer, Decimal, Boolean, Blob, AutoIncrement, 
	CreatedBy, UpdatedBy, CreationDate, UpdateDate, UpdateProcess, UUID;
	
	public boolean isWho() {
		if (this == CreatedBy)
			return true;
		if (this == UpdatedBy)
			return true;
		if (this == CreationDate)
			return true;
		if (this == UpdateDate)
			return true;
		if (this == UpdateProcess)
			return true;
		return false;
	}
}
