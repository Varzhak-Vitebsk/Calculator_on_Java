package calculator;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Calculator {
	// Main class. Creates and fills parent frame.
	
	private JFrame main_frame;
	private int frame_width, frame_height;
	
	private final int MIN_FRAME_WIDTH = 400;
	private final int MIN_FRAME_HEIGHT = 300;
	private final int PART_OF_DISPLAY = 4;
	
	public Calculator() {
		main_frame = new JFrame("Calculator");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		frame_width = gd.getDisplayMode().getWidth() / PART_OF_DISPLAY;
		frame_height = gd.getDisplayMode().getHeight() / PART_OF_DISPLAY;
		main_frame.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		main_frame.setSize(frame_width, frame_height);
		JPanel back_panel = new JPanel(new GridBagLayout());
		createStandartUI(back_panel);
	}
	
	public void start() {
		main_frame.setVisible(true);
	}
	
	private void createStandartUI(JPanel back_panel) {
		JPanel display_panel = new JPanel();
		
		JPanel digit_buttons_panel = new JPanel(new GridBagLayout());
		JPanel operator_buttons_panel = new JPanel(new GridBagLayout());
		
	}

}
