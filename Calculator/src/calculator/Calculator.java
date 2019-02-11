package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class Calculator {
	// Main class. Creates and fills parent frame.
	
	private JFrame main_frame;
	private double left_operand, right_operand;
	private boolean left_in_use;
	private byte left_dot_flag, left_fràction_digits, right_dot_flag, right_fraction_digits;
	private JLabel display;
	private Object current_operator;
	
	private final int MIN_FRAME_WIDTH = 270;
	private final int MIN_FRAME_HEIGHT = 200;
	private final int PART_OF_DEVICE_DISPLAY = 4;
	private final int SIZE_OF_DISPLAY_FONT = 20;
	private final int BORDER_OF_BACKPANEL = 5;
	private final int BORDER_OF_INNERPANEL = 1;
	private final int DISPLAY_SYMBOL_LIMIT = 20;
	
	public Calculator() {
		
		left_operand = 0;
		right_operand = 0;
		left_in_use = true;
		left_dot_flag = 0;
		left_fràction_digits = 0;
		right_dot_flag = 0;
		right_fraction_digits = 0;
		current_operator = null;
		
		main_frame = new JFrame("Calculator");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		main_frame.setMinimumSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		main_frame.setPreferredSize(new Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT));
		main_frame.setLocation(gd.getDisplayMode().getWidth() / PART_OF_DEVICE_DISPLAY
				, gd.getDisplayMode().getHeight() / PART_OF_DEVICE_DISPLAY);
		main_frame.setResizable(false);
		JPanel back_panel = new JPanel(new GridBagLayout());
		back_panel.setBackground(Color.WHITE);
		createStandardUI(back_panel);		
		main_frame.add(back_panel);
	}
	
	public void start() {
		main_frame.setVisible(true);
	}
	
	private void createStandardUI(JPanel back_panel) {
		// Creates standard UI which includes display, digit buttons and basic operator buttons 
		// Creates and fills display panel
		JPanel display_panel = new JPanel(new GridBagLayout());
		display_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		display = new JLabel("0", SwingConstants.RIGHT);
		display.setFont(new Font(Font.MONOSPACED, Font.BOLD, SIZE_OF_DISPLAY_FONT));
		GridBagConstraints constraits = new GridBagConstraints();
		constraits.fill = GridBagConstraints.HORIZONTAL;	
		constraits.weightx = 0.5;		
		display_panel.add(display, constraits);
		
		// Creates and fills digit buttons panel
		JPanel symbol_buttons_panel = new JPanel(new GridBagLayout());
		symbol_buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		constraits.insets = new Insets(BORDER_OF_INNERPANEL
				, BORDER_OF_INNERPANEL
				, BORDER_OF_INNERPANEL
				, BORDER_OF_INNERPANEL);
		var symbol_button_listener = new SymbolButtonActionListener();
		for(byte row = 0; row < 3; ++row) {			
			for(byte ind = 7; ind < 10; ++ind){
				JButton button = new JButton(String.valueOf(ind - 3 * row));
				button.setActionCommand(String.valueOf(ind - 3 * row));
				button.addActionListener(symbol_button_listener);
				constraits.gridy = row;
				symbol_buttons_panel.add(button, constraits);
			}
		}
		JButton button = new JButton("0");
		button.setActionCommand("0");
		button.addActionListener(symbol_button_listener);
		constraits.gridy = 3;
		constraits.gridwidth = GridBagConstraints.RELATIVE;
		symbol_buttons_panel.add(button, constraits);
		button = new JButton(".");
		button.setActionCommand(".");
		button.addActionListener(new DotButtonActionListener());
		symbol_buttons_panel.add(button, constraits);
		
		// Creates and fills operator buttons panel
		JPanel operator_buttons_panel = new JPanel(new GridBagLayout());
		operator_buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		constraits.gridy = 0;
		operator_buttons_panel.add(new JButton("Del"), constraits);
		var clear_button_listener = new ClearButtonActionListener();
		button = new JButton("C");
		button.setActionCommand("C");
		button.addActionListener(clear_button_listener);
		operator_buttons_panel.add(button, constraits);
		
		var operator_button_listener = new OperatorButtonActionListener();
		constraits.gridy = 1;		
		constraits.gridwidth = 1;
		button = new JButton("/");
		button.setActionCommand("/");
		button.addActionListener(operator_button_listener);
		operator_buttons_panel.add(button, constraits);
		
		button = new JButton("*");
		button.setActionCommand("*");
		button.addActionListener(operator_button_listener);
		operator_buttons_panel.add(button, constraits);	
		
		constraits.gridy = 2;
		button = new JButton("-");
		button.setActionCommand("-");
		button.addActionListener(operator_button_listener);
		operator_buttons_panel.add(button, constraits);
		
		button = new JButton("+");
		button.setActionCommand("+");
		button.addActionListener(operator_button_listener);
		operator_buttons_panel.add(button, constraits);	
		
		constraits.gridy = 3;		
		constraits.gridwidth = GridBagConstraints.REMAINDER;
		var result_button_listener = new ResultButtonActionListener();
		button = new JButton("=");
		button.setActionCommand("=");
		button.addActionListener(result_button_listener);
		operator_buttons_panel.add(button, constraits);		
		
		// Fills frame back panel		
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
	
	private class SymbolButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			byte digit = 10;
			if(left_in_use) {
				if (left_dot_flag == 1) {
					++left_fràction_digits;
					digit = 1;
				}
				if(display.getText().length() >= DISPLAY_SYMBOL_LIMIT) return;
				if((left_operand == 0) && (left_dot_flag == 0)) display.setText(e.getActionCommand());
				else display.setText(display.getText() + e.getActionCommand());
				left_operand *= digit;
				left_operand += Double.valueOf(e.getActionCommand()) * Math.pow(10, -left_fràction_digits);				
			}
			else {
				if (right_dot_flag == 1) {
					++right_fraction_digits;
					digit = 1;
				}
				if(display.getText().length() >= DISPLAY_SYMBOL_LIMIT) return;
				if((right_operand == 0) && (right_dot_flag == 0)) display.setText(e.getActionCommand());
				else display.setText(display.getText() + e.getActionCommand());
				right_operand *= digit;
				right_operand += Double.valueOf(e.getActionCommand()) * Math.pow(10, -right_fraction_digits);
			}			
		}		
	}
	
	private class DotButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if((left_in_use) && (left_fràction_digits == 0)) {
				if (left_dot_flag == 0) display.setText(display.getText() + e.getActionCommand());
				else display.setText(display.getText().substring(0, display.getText().length() - 1)); 
				left_dot_flag ^= 1;
			}
			if((!left_in_use) && (right_fraction_digits == 0)) {
				if (right_dot_flag == 0) display.setText(display.getText() + e.getActionCommand());
				else display.setText(display.getText().substring(0, display.getText().length() - 1));
				right_dot_flag ^= 1;
			}
		}		
	}
	
	private class OperatorButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			current_operator = e.getSource();
			if(left_in_use) {
				left_in_use = false;
				display.setText("0");
			}
			else {
				switch(e.getActionCommand()) {
				case("+"):
					left_operand += right_operand;
					break;
				case("-"):
					left_operand -= right_operand;
					break;
				case("*"):
					left_operand *= right_operand;
					break;
				case("/"):
					left_operand /= right_operand;
					break;
				case("C"):
					left_operand = 0;
					left_fràction_digits = 0;
					left_in_use = true;
					break;
				}				
				if (left_operand == Math.floor(left_operand)) {
					left_dot_flag = 0;
					left_fràction_digits = 0;
					display.setText(String.valueOf(left_operand).substring(0, String.valueOf(left_operand).length() - 2));
				}
				else {
					left_dot_flag = 1;
					int fraction_digits = (display.getText().length() - display.getText().indexOf("."));
					left_fràction_digits = (fraction_digits > 127 ? 127 : (byte)fraction_digits);
					display.setText(String.valueOf(left_operand));
				}
				right_operand = 0;
				right_fraction_digits = 0;
				right_dot_flag = 0;
			}
		}
	}
	
	private class ResultButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(current_operator == null) return;
			try {
				JButton button = (JButton)current_operator;
				ActionListener[] listners = button.getActionListeners();
				listners[0].actionPerformed(new ActionEvent(button, ActionEvent.ACTION_FIRST, button.getActionCommand()));
			}
			catch(ClassCastException e) {
				// must think of some action
			}			
			left_in_use = true;
			current_operator = null;
		}
	}
	
	private class ClearButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			left_operand = 0;
			left_fràction_digits = 0;
			right_operand = 0;
			right_fraction_digits = 0;
			left_dot_flag = 0;
			right_dot_flag = 0;
			left_in_use = true;
			current_operator = null;
			display.setText("0");
		}
	}
	
	private class DeleteButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
