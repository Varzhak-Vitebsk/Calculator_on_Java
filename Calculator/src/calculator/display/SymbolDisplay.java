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
	private HashMap<String, Symbol> symbols;

	public SymbolDisplay() {
		setPreferredSize(new Dimension(Calculator.MIN_FRAME_WIDTH, 23));
		symbol_block_size = new Dimension(this.getPreferredSize().height, this.getPreferredSize().height);
		display_capacity = this.getPreferredSize().width / symbol_block_size.width;
		display_queue = new ArrayList<DisplaySymbolBlock>(display_capacity);
		symbols = new HashMap<String, Symbol>();
		symbols.put("9", new DigitNine(symbol_block_size, "9"));
		symbols.put("8", new DigitEight(symbol_block_size, "8"));
		symbols.put("7", new DigitSeven(symbol_block_size, "7"));
		symbols.put("6", new DigitSix(symbol_block_size, "6"));
		symbols.put("5", new DigitFive(symbol_block_size, "5"));
		symbols.put("4", new DigitFour(symbol_block_size, "4"));
		symbols.put("3", new DigitThree(symbol_block_size, "3"));
		symbols.put("2", new DigitTwo(symbol_block_size, "2"));
		symbols.put("1", new DigitOne(symbol_block_size, "1"));
		symbols.put("0", new DigitZero(symbol_block_size, "0"));
		symbols.put(" ", new Empty(symbol_block_size, " "));
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
		int dot_pos = s.indexOf(".");
		String new_s = s.replaceAll("\\W", "");
		if(new_s.length() < display_capacity)
			new_s = " ".repeat(display_capacity - new_s.length()) + new_s;
		for(int display_ind = 0, s_ind = 0
				; display_ind < display_capacity && s_ind < new_s.length()
				; ++display_ind, ++s_ind) {
			String cur_symbol = new_s.substring(s_ind, s_ind + 1);
			display_queue.get(display_ind).setSymbol(symbols.get(cur_symbol));
			if(display_ind == dot_pos) {
				if(display_queue.get(display_ind - 1).getSymbol().contentEquals(" "))
					display_queue.get(display_ind - 1).setSymbol(symbols.get("0"));
				display_queue.get(display_ind).activateDot();
			}
		}
		repaint();
	}
	
	public String getText() {
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
		repaint();
	}
	
	private void initDisplay() {
		for(int ind = 0; ind < display_capacity; ++ind) {
			var displaySymbolBlock = new DisplaySymbolBlock(symbol_block_size);
			displaySymbolBlock.setSymbol(symbols.get(" "));			
			display_queue.add(displaySymbolBlock);
		}
		current_symbol_block = display_capacity;
	}
}
