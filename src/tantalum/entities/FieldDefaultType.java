package tantalum.entities;

public enum FieldDefaultType {
	Hard, Soft, Identical;

	public boolean isHard() {
		return this == Hard;
	}
}
