package calculator.buttons;

import javax.swing.Action;
import javax.swing.Icon;

public final class OperatorPlusButton extends OperatorButton {

	public OperatorPlusButton() {
		// TODO Auto-generated constructor stub
	}

	public OperatorPlusButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public OperatorPlusButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public OperatorPlusButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public OperatorPlusButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double operationResult(double left_operand, double right_operand) {		
		return left_operand + right_operand;
	}

}
