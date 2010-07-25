package tantalum.entities;

public enum Direction {
	Horizontal, Vertical;

	public boolean isVertical() {
		return this == Vertical;
	}
}
