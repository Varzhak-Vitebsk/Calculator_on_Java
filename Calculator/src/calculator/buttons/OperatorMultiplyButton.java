package calculator.buttons;

import javax.swing.Action;
import javax.swing.Icon;

public final class OperatorMultiplyButton extends OperatorButton {

	public OperatorMultiplyButton() {
		// TODO Auto-generated constructor stub
	}

	public OperatorMultiplyButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public OperatorMultiplyButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public OperatorMultiplyButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public OperatorMultiplyButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double operationResult(double left_operand, double right_operand) {
		return left_operand * right_operand;
	}

}
