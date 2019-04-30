package calculator.display;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calculator.Calculator;
public class DisplayTest {
	
	private JFrame main_frame;
	private SymbolDisplay display;
	
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
		JOptionPane.showMessageDialog(new JFrame(), "Test Initialization: 0", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		display.replace("0123456789");
		JOptionPane.showMessageDialog(new JFrame(), "Test: 0123456789", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		display.replace("0123456.789");
		JOptionPane.showMessageDialog(new JFrame(), "Test: 0123456.789", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
		display.nullify();
		JOptionPane.showMessageDialog(new JFrame(), "Test Nullify: 0", "Dialog",
		        JOptionPane.INFORMATION_MESSAGE);
	}
}
