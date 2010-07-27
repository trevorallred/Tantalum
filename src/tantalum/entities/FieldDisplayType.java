package tantalum.entities;

public enum FieldDisplayType {
	Text, TextArea, Checkbox, Radio, Select;
	public boolean isCheckbox() {
		return this == Checkbox;
	}
}
