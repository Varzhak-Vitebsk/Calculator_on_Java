package calculator;

import javax.swing.JFrame;

public final class Calculator {
	// Main class. Creates and fills parent frame.
	
	private JFrame main_frame;
	private int frame_width, frame_height;
	
	public Calculator() {
		main_frame = new JFrame("Calculator");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void start() {
		main_frame.setVisible(true);
	}

}
