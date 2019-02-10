package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
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
	
	private final int MIN_FRAME_WIDTH = 250;
	private final int MIN_FRAME_HEIGHT = 200;
	private final int PART_OF_DEVICE_DISPLAY = 4;
	private final int SIZE_OF_DISPLAY_FONT = 20;
	private final int BORDER_OF_BACKPANEL = 5;
	private final int BORDER_OF_INNERPANEL = 1;
	
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
		main_frame.setPreferredSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		main_frame.setLocation(frame_width, frame_height);
		main_frame.setResizable(false);
		JPanel back_panel = new JPanel(new GridBagLayout());
		back_panel.setBackground(Color.WHITE);
		createStandartUI(back_panel);		
		main_frame.add(back_panel);
	}
	
	public void start() {
		main_frame.setVisible(true);
	}
	
	private void createStandartUI(JPanel back_panel) {
		// Create and fill display panel
		JPanel display_panel = new JPanel(new GridBagLayout());
		display_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		display = new JLabel(String.valueOf(left_operand), SwingConstants.RIGHT);
		display.setFont(new Font(Font.MONOSPACED, Font.BOLD, SIZE_OF_DISPLAY_FONT));
		GridBagConstraints constraits = new GridBagConstraints();
		constraits.fill = GridBagConstraints.HORIZONTAL;	
		constraits.weightx = 0.5;		
		display_panel.add(display, constraits);
		
		// Create and fill digit buttons panel
		JPanel symbol_buttons_panel = new JPanel(new GridBagLayout());
		symbol_buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		constraits.insets = new Insets(BORDER_OF_INNERPANEL
				, BORDER_OF_INNERPANEL
				, BORDER_OF_INNERPANEL
				, BORDER_OF_INNERPANEL);
		for(byte row = 0; row < 3; ++row) {			
			for(byte ind = 7; ind < 10; ++ind){
				JButton button = new JButton(String.valueOf(ind - 3 * row));				
				constraits.gridy = row;
				symbol_buttons_panel.add(button, constraits);
			}
		}
		JButton button = new JButton("0");		
		constraits.gridy = 3;
		constraits.gridwidth = GridBagConstraints.RELATIVE;
		symbol_buttons_panel.add(button, constraits);		
		symbol_buttons_panel.add(new JButton("."), constraits);
		
		// Create and fill operator buttons panel
		JPanel operator_buttons_panel = new JPanel(new GridBagLayout());
		operator_buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		constraits.gridy = 0;
		constraits.gridwidth = GridBagConstraints.REMAINDER;
		operator_buttons_panel.add(new JButton("Delete"), constraits);		
		constraits.gridy = 1;		
		constraits.gridwidth = 1;
		operator_buttons_panel.add(new JButton("/"), constraits);
		operator_buttons_panel.add(new JButton("*"), constraits);		
		constraits.gridy = 2;		
		operator_buttons_panel.add(new JButton("-"), constraits);
		operator_buttons_panel.add(new JButton("+"), constraits);		
		constraits.gridy = 3;		
		constraits.gridwidth = GridBagConstraints.REMAINDER;
		operator_buttons_panel.add(new JButton("="), constraits);
		
		// Fill frame back panel		
		constraits.gridy = 0;		
		constraits.gridwidth = 5;
		constraits.insets = new Insets(BORDER_OF_BACKPANEL
				, BORDER_OF_BACKPANEL
				, BORDER_OF_BACKPANEL
				, BORDER_OF_BACKPANEL);
		back_panel.add(display_panel, constraits);		
		constraits.gridy = 1;
		constraits.gridwidth = 3;
		back_panel.add(symbol_buttons_panel, constraits);
		constraits.gridwidth = 2;
		back_panel.add(operator_buttons_panel, constraits);		
	}

}
