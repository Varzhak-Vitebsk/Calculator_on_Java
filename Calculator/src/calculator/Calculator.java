package calculator;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class Calculator {
	// Main class. Creates and fills parent frame.
	
	private JFrame main_frame;
	private int frame_width, frame_height;
	private double left_operand, right_operand;
	private JLabel display;
	
	private final int MIN_FRAME_WIDTH = 400;
	private final int MIN_FRAME_HEIGHT = 300;
	private final int PART_OF_DEVICE_DISPLAY = 4;
	
	public Calculator() {
		left_operand = 0;
		right_operand = 0;
		main_frame = new JFrame("Calculator");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		frame_width = gd.getDisplayMode().getWidth() / PART_OF_DEVICE_DISPLAY;
		frame_height = gd.getDisplayMode().getHeight() / PART_OF_DEVICE_DISPLAY;
		main_frame.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		main_frame.setSize(frame_width, frame_height);
		JPanel back_panel = new JPanel(new GridBagLayout());
		createStandartUI(back_panel);
		main_frame.add(back_panel);
	}
	
	public void start() {
		main_frame.setVisible(true);
	}
	
	private void createStandartUI(JPanel back_panel) {
		// Create and fill display panel
		JPanel display_panel = new JPanel();
		display = new JLabel(String.valueOf(left_operand), SwingConstants.RIGHT);
		display_panel.add(display);
		// Create and fill digit buttons panel
		JPanel digit_buttons_panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraits = new GridBagConstraints();
		for(byte row = 0; row < 3; ++row) {			
			for(byte ind = 7; ind < 10; ++ind){
				JButton button = new JButton(String.valueOf(ind - 3 * row));
				constraits.fill = GridBagConstraints.HORIZONTAL;
				constraits.gridy = row;
				digit_buttons_panel.add(button, constraits);
			}
		}
		JButton button = new JButton("0");
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 3;
		digit_buttons_panel.add(button, constraits);
		// Create and fill operator buttons panel
		JPanel operator_buttons_panel = new JPanel(new GridBagLayout());
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 0;
		operator_buttons_panel.add(new JButton("/"), constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 0;
		operator_buttons_panel.add(new JButton("*"), constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 1;
		operator_buttons_panel.add(new JButton("-"), constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 1;
		operator_buttons_panel.add(new JButton("+"), constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 2;
		operator_buttons_panel.add(new JButton("="), constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 2;
		operator_buttons_panel.add(new JButton("."), constraits);
		// Fill frame back panel
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 0;
		back_panel.add(display_panel, constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 1;
		back_panel.add(digit_buttons_panel, constraits);
		constraits.fill = GridBagConstraints.HORIZONTAL;
		constraits.gridy = 1;
		back_panel.add(operator_buttons_panel, constraits);
	}

}
