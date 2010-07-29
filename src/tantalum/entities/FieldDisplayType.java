package tantalum.entities;

public enum FieldDisplayType {
	Text, TextArea, Checkbox, Radio, Select;
	public boolean isCheckbox() {
		return this == Checkbox;
	}
	
	/**
	 * See <a href="http://www.yaml.de/en/documentation/css-components/form-construction-kit.html">YAML Form Construction</a>
	 * @return
	 */
	public String getYamlClass() {
		if (this == Select)
			return "type-select";
		if (this == Checkbox)
			return "type-check";
		if (this == Radio)
			return "type-check";
		return "type-text";
	}
}
