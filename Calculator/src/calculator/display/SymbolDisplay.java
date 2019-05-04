package calculator.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import calculator.Calculator;

import java.util.ArrayList;
import java.util.HashMap;

public final class SymbolDisplay extends JPanel {
	
	private Dimension symbol_block_size;
	private int display_capacity;
	private ArrayList<DisplaySymbolBlock> display_queue;
	private int current_symbol_block;
//	private boolean is_negative;
	private HashMap<String, Symbol> symbols;

	public SymbolDisplay() {
		setPreferredSize(new Dimension(Calculator.MIN_FRAME_WIDTH, 23));
		symbol_block_size = new Dimension(this.getPreferredSize().height, this.getPreferredSize().height);
		display_capacity = this.getPreferredSize().width / symbol_block_size.width;
		display_queue = new ArrayList<DisplaySymbolBlock>(display_capacity);
		symbols = new HashMap<String, Symbol>();
		symbols.put("9", new SymbolNine(symbol_block_size, "9"));
		symbols.put("8", new SymbolEight(symbol_block_size, "8"));
		symbols.put("7", new SymbolSeven(symbol_block_size, "7"));
		symbols.put("6", new SymbolSix(symbol_block_size, "6"));
		symbols.put("5", new SymbolFive(symbol_block_size, "5"));
		symbols.put("4", new SymbolFour(symbol_block_size, "4"));
		symbols.put("3", new SymbolThree(symbol_block_size, "3"));
		symbols.put("2", new SymbolTwo(symbol_block_size, "2"));
		symbols.put("1", new SymbolOne(symbol_block_size, "1"));
		symbols.put("0", new SymbolZero(symbol_block_size, "0"));
		symbols.put(" ", new SymbolEmpty(symbol_block_size, " "));
		symbols.put("-", new SymbolMinus(symbol_block_size, "-"));
		symbols.put("N", new SymbolN(symbol_block_size, "N"));
		symbols.put("a", new SymbolA(symbol_block_size, "a"));
		initDisplay();
		nullify();
	}

	public SymbolDisplay(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public SymbolDisplay(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public SymbolDisplay(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public int maxCapacity() {
		return display_capacity;
	}
	
	public int currentCapacity() {
		int capacity = 0;
		for(var displaySymbolBlock: display_queue) {
			if(!displaySymbolBlock.getSymbol().isEmpty()) ++capacity;
		}
		return capacity;
	}
	
	public int dotPosition() {
		for (var displaySymbolBlock : display_queue) {
			if(displaySymbolBlock.hasDot()) return display_queue.indexOf(displaySymbolBlock);
		} 
		return -1;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int position = 0;
        for(var displaySymbolBlock: display_queue) 
        	displaySymbolBlock.draw(g
        		, position++
        		, this.getWidth() - (display_capacity - 1) * symbol_block_size.width
        		, 0);
    }
	
	public void dotReverse() {
		int dot_position = dotPosition();
		if(dot_position == -1) display_queue.get(display_capacity - 1).activateDot();
		else if (dot_position == display_capacity - 1) display_queue.get(display_capacity - 1).deactivateDot();		
	}
	
	public int addSymbol(String s) {
		//Add symbol to display stack, move other symbols to left
		if(current_symbol_block < 0) return current_symbol_block;
		display_queue.get(current_symbol_block).setSymbol(symbols.get(s));
		return current_symbol_block--;
	}
	
	public void removeSymbol() {
		//Remove symbol from display stack, move other symbols to right
	}
	
	public void replace(String s) {
		//Refill display stack
		
//		Have to handle these:
//		1.If the argument is NaN, the result is the string "NaN". 
//		2.Otherwise, the result is a string that represents the sign and magnitude (absolute value) of the argument.
//			2.1.1.If the sign is negative, the first character of the result is '-'('\u005Cu002D');
//			2.1.2.if the sign is positive, no sign character appears in the result.
//			2.2.As for the magnitude m:
//			2.2.1.If m is infinity, it is represented by the characters "Infinity"; thus, positive infinity produces the result "Infinity" and negative infinity produces the result "-Infinity". 
//			2.2.2.If m is zero, it is represented by the characters "0.0"; thus, negative zero produces the result "-0.0" and positive zero produces the result "0.0". 
//			2.2.3.If m is greater than or equal to 10^-3 but less than 10^7, then it is represented as the integer part of m, in decimal form with no leading zeroes, followed by'.' ('\u005Cu002E'),
//				followed by one or more decimal digits representing the fractional part of m. 
//			2.2.4.If m is less than 10^-3 or greater than or equal to 10^7, then it is represented in so-called "computerized scientific notation."
//				Let n be the unique integer such that 10n <= m < 10n+1; then let a be the mathematically exact quotient of m and 10n so that 1 <= a < 10.
//				The magnitude is then represented as the integer part of a, as a single decimal digit, followed by '.'('\u005Cu002E'),
//				followed by decimal digits representing the fractional part of a, followed by the letter 'E' ('\u005Cu0045'), followed by a representation of n as a decimal integer, as produced by the method 
		
		nullify();
		if(s.contentEquals("NaN")) {
			for(int s_ind = 0; s_ind < s.length(); ++s_ind) {
				display_queue.get(display_capacity - s.length() + s_ind).setSymbol(symbols.get(s.substring(s_ind, s_ind + 1)));
			}
			repaint();
			return;
		}
//		if(s.startsWith("-")) is_negative = true;
//		else is_negative = false;
		int dot_pos = s.indexOf(".");
		int has_dot = dot_pos < 0? 0: 1;
		for(int s_ind = 0, display_ind = display_capacity - (s.length() - has_dot);
				s_ind < s.length() && (display_ind < display_capacity && display_ind > 0);
				++s_ind, ++display_ind) {
			String curr_symbol = s.substring(s_ind, s_ind + 1);
			if(curr_symbol.equals(".")) {
				display_queue.get(--display_ind).activateDot();
				continue;
			}
			display_queue.get(display_ind).setSymbol(symbols.get(curr_symbol));
		}
		repaint();
	}
	
	public String getText() {
		
//		Have to handle these:
//		1.If the argument is NaN, the result is the string "NaN". 
//		2.Otherwise, the result is a string that represents the sign and magnitude (absolute value) of the argument.
//			2.1.1.If the sign is negative, the first character of the result is '-'('\u005Cu002D');
//			2.1.2.if the sign is positive, no sign character appears in the result.
//			2.2.As for the magnitude m:
//			2.2.1.If m is infinity, it is represented by the characters "Infinity"; thus, positive infinity produces the result "Infinity" and negative infinity produces the result "-Infinity". 
//			2.2.2.If m is zero, it is represented by the characters "0.0"; thus, negative zero produces the result "-0.0" and positive zero produces the result "0.0". 
//			2.2.3.If m is greater than or equal to 10^-3 but less than 10^7, then it is represented as the integer part of m, in decimal form with no leading zeroes, followed by'.' ('\u005Cu002E'),
//				followed by one or more decimal digits representing the fractional part of m. 
//			2.2.4.If m is less than 10^-3 or greater than or equal to 10^7, then it is represented in so-called "computerized scientific notation."
//				Let n be the unique integer such that 10n <= m < 10n+1; then let a be the mathematically exact quotient of m and 10n so that 1 <= a < 10.
//				The magnitude is then represented as the integer part of a, as a single decimal digit, followed by '.'('\u005Cu002E'),
//				followed by decimal digits representing the fractional part of a, followed by the letter 'E' ('\u005Cu0045'), followed by a representation of n as a decimal integer, as produced by the method
		
		String result = new String("");
		for (var displaySymbolBlock : display_queue) {
			result = result + displaySymbolBlock.getSymbol();
			if (displaySymbolBlock.hasDot()) result = result + ".";
		}
		return result;
	}
	
	public void nullify() {
		for(var displaySymbolBlock: display_queue) { 
			displaySymbolBlock.setSymbol(symbols.get(" "));
			displaySymbolBlock.deactivateDot();
		}
		display_queue.get(display_capacity - 1).setSymbol(symbols.get("0"));
		current_symbol_block = display_capacity - 1;
//		is_negative = false;
		repaint();
	}
	
	private void initDisplay() {
		for(int ind = 0; ind < display_capacity; ++ind) {
			var displaySymbolBlock = new DisplaySymbolBlock();
			displaySymbolBlock.setSymbol(symbols.get(" "));			
			display_queue.add(displaySymbolBlock);
		}
		current_symbol_block = display_capacity;
//		is_negative = false;
	}
}
