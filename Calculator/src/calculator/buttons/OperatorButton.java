package calculator.buttons;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

abstract public class OperatorButton extends JButton {

	public OperatorButton() {
		// TODO Auto-generated constructor stub
	}

	public OperatorButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public OperatorButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public OperatorButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public OperatorButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	
	abstract public double operationResult(double left_operand, double right_operand);

}
