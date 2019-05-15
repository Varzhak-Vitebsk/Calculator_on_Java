package calculator.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calculator.Calculator;
public class DisplayTest {
	
	private JFrame main_frame;
	private SymbolDisplay display;
//	private double[] test_cases = {0, -0, 12, -12, 90., -0.0, .13, -0.13, 1.13, -1.13, 1e-9d, -1e-9d, 1e137, Double.NaN, -Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY};
	private double[] test_cases = {105.83333333333333, 1234567890.};
	
	public DisplayTest() {
		main_frame = new JFrame("Calculator Display Test");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		main_frame.setMinimumSize(new Dimension(Calculator.MIN_FRAME_WIDTH
				, Calculator.MIN_FRAME_HEIGHT));
		main_frame.setPreferredSize(new Dimension(Calculator.MIN_FRAME_WIDTH
				, Calculator.MIN_FRAME_HEIGHT));
		main_frame.setLocation(gd.getDisplayMode().getWidth() / Calculator.PART_OF_DEVICE_DISPLAY
				, gd.getDisplayMode().getHeight() / Calculator.PART_OF_DEVICE_DISPLAY);
		main_frame.setResizable(false);
		display = new SymbolDisplay();
		main_frame.add(display);
		display.repaint();
	}
	
	public void start() {
		main_frame.setVisible(true);
		JOptionPane.showMessageDialog(new JFrame(), "Test initialization: 0", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		display.replace("0123456789.");
		JOptionPane.showMessageDialog(new JFrame(), "Test Digits: 0123456789.", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		display.nullify();
		JOptionPane.showMessageDialog(new JFrame(), "Test nullify: 0", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		for(double d: test_cases) {
			display.replace(String.valueOf(d));
			JOptionPane.showMessageDialog(new JFrame(), "Test replace: " + String.valueOf(d), "Dialog",
					JOptionPane.INFORMATION_MESSAGE);
			boolean get_result = (d == Double.valueOf(display.getText())) || (Double.isNaN(Double.valueOf(display.getText())));
			JOptionPane.showMessageDialog(new JFrame(), "Test getText: " + String.valueOf(d) + " Res: " + get_result, "Dialog",
					JOptionPane.INFORMATION_MESSAGE);			
		}
	}
}
