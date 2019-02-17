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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public final class Calculator {
	// Main class. Creates and fills parent frame.
	
	private JFrame main_frame;
	private double left_operand, right_operand;
	private boolean left_in_use;
	private byte right_operator_input_flag;
	private JLabel display;
	private Object current_operator_object;
	private String current_operator;
	
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
		right_operator_input_flag = 0;
		current_operator_object = null;
		current_operator = "";
		
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
				button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke(String.valueOf(ind - 3 * row)), "pressed");
				button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke("released " + String.valueOf(ind - 3 * row)), "released");
				button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("NUMPAD" + String.valueOf(ind - 3 * row)), "pressed");
			button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("released NUMPAD" + String.valueOf(ind - 3 * row)), "released");
				constraits.gridy = row;
				symbol_buttons_panel.add(button, constraits);
			}
		}
		JButton button = new JButton("0");
		button.setActionCommand("0");
		button.addActionListener(symbol_button_listener);
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("0"), "pressed");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("released 0"), "released");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("NUMPAD0"), "pressed");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("released NUMPAD0"), "released");
		constraits.gridy = 3;
		constraits.gridwidth = GridBagConstraints.RELATIVE;
		symbol_buttons_panel.add(button, constraits);
		button = new JButton(".");
		button.setActionCommand(".");
		button.addActionListener(new DotButtonActionListener());
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed ."), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released PERIOD"), "released");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed ,"), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released COMMA"), "released");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke((char)KeyEvent.VK_SEPARATOR), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released DECIMAL"), "released");
		symbol_buttons_panel.add(button, constraits);
		
		// Creates and fills operator buttons panel
		JPanel operator_buttons_panel = new JPanel(new GridBagLayout());
		operator_buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		constraits.gridy = 0;
		button = new JButton("Del");
		button.setActionCommand("Del");
		button.addActionListener(new DeleteButtonActionListener());
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke((char)KeyEvent.VK_DELETE), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released DELETE"), "released");
		operator_buttons_panel.add(button, constraits);
		
		button = new JButton("C");
		button.setActionCommand("C");
		button.addActionListener(new ClearButtonActionListener());
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed c"), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released C"), "released");
		operator_buttons_panel.add(button, constraits);
		
		var operator_button_listener = new OperatorButtonActionListener();
		constraits.gridy = 1;		
		constraits.gridwidth = 1;
		button = new JButton("/");
		button.setActionCommand("/");
		button.addActionListener(operator_button_listener);
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed /"), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released DIVIDE"), "released");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released SLASH"), "released");
		operator_buttons_panel.add(button, constraits);
		
		button = new JButton("*");
		button.setActionCommand("*");
		button.addActionListener(operator_button_listener);
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed *"), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released MULTIPLY"), "released");
		operator_buttons_panel.add(button, constraits);	
		
		constraits.gridy = 2;
		button = new JButton("-");
		button.setActionCommand("-");
		button.addActionListener(operator_button_listener);
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed -"), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released MINUS"), "released");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released SUBTRACT"), "released");
		operator_buttons_panel.add(button, constraits);
		
		button = new JButton("+");
		button.setActionCommand("+");
		button.addActionListener(operator_button_listener);
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed +"), "pressed");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.SHIFT_DOWN_MASK, true), "released");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released ADD"), "released");
		operator_buttons_panel.add(button, constraits);	
		
		constraits.gridy = 3;		
		constraits.gridwidth = GridBagConstraints.REMAINDER;
		button = new JButton("=");
		button.setActionCommand("=");
		button.addActionListener(new ResultButtonActionListener());
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("typed ="), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released EQUALS"), "released");
		button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		button.getInputMap(/*JComponent.WHEN_IN_FOCUSED_WINDOW*/)
			.put(KeyStroke.getKeyStroke("released ENTER"), "released");
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
			if(left_in_use) {
				if(display.getText().length() >= DISPLAY_SYMBOL_LIMIT) return;
				if((left_operand == 0)
						&& (display.getText().indexOf(".") == - 1)
						&& (Double.valueOf(display.getText()) == 0))
					display.setText(e.getActionCommand());
				else display.setText(display.getText() + e.getActionCommand());
			}
			else {
				if (right_operator_input_flag == 0) {
					right_operator_input_flag ^= 1;
					display.setText(e.getActionCommand());
					
				}
				else if(display.getText().length() >= DISPLAY_SYMBOL_LIMIT) return;
				else display.setText(display.getText() + e.getActionCommand());
			}			
		}		
	}
	
	private class DotButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(display.getText().contains("NaN")) return;
			if(display.getText().contains("Infinity")) return;
			if(display.getText().indexOf(".") == display.getText().length() - 1) {
				display.setText(display.getText().substring(0, display.getText().length() - 1));
				return;
			}
			if(display.getText().indexOf(".") == - 1)
				display.setText(display.getText() + ".");
		}		
	}
	
	private class OperatorButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			current_operator_object = e.getSource();
			if(left_in_use) {
				left_in_use = false;
				left_operand = Double.valueOf(display.getText());
				display.setText("0");
			}
			else {
				right_operand = Double.valueOf(display.getText());
				switch(current_operator.length() == 0 ? e.getActionCommand() : current_operator) {
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
					left_in_use = true;
					break;
				}
				display.setText(String.valueOf(left_operand));
				//Rounding display if number is integer
				if (left_operand == Math.floor(left_operand)){
					if (display.getText().indexOf("E") == -1) {
						display.setText(display.getText().
								substring(0, display.getText().length() - 2));
					}
				}
				right_operand = 0;
				right_operator_input_flag = 0;
			}
			current_operator = e.getActionCommand();
		}
	}
	
	private class ResultButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(current_operator_object == null) return;
			try {
				JButton button = (JButton)current_operator_object;
				ActionListener[] listners = button.getActionListeners();
				listners[0].actionPerformed(new ActionEvent(button, ActionEvent.ACTION_FIRST, button.getActionCommand()));
			}
			catch(ClassCastException e) {
				// must think of some action
			}			
			left_in_use = true;
			current_operator_object = null;
			current_operator = "";
		}
	}
	
	private class ClearButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			left_operand = 0;
			right_operand = 0;
			right_operator_input_flag = 0;
			left_in_use = true;
			current_operator_object = null;
			current_operator = "";
			display.setText("0");
		}
	}
	
	private class DeleteButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(display.getText().length() == 1)
				display.setText("0");
			if(display.getText().length() > 1)
				display.setText(display.getText().substring(0, display.getText().length() - 1));
				if(display.getText().indexOf(".") == display.getText().length() - 1)
					display.setText(display.getText().substring(0, display.getText().length() - 1));
			if(left_in_use) left_operand = Double.valueOf(display.getText());
			else right_operand = Double.valueOf(display.getText());
		}
	}
}
