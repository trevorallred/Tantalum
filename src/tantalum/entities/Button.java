package tantalum.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tan_button")
public class Button extends BaseNamedTable {
	@ManyToOne
	@JoinColumn(name = "viewID")
	private View view;
	@Enumerated(EnumType.STRING)
	private ButtonType buttonType;
	private String onClick;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public ButtonType getButtonType() {
		return buttonType;
	}

	public void setButtonType(ButtonType buttonType) {
		this.buttonType = buttonType;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

}
