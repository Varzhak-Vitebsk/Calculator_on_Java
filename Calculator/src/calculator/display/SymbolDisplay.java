package calculator.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import calculator.Calculator;

import java.util.ArrayList;

public final class SymbolDisplay extends JPanel {
	
	private Dimension symbol_block_size;
	private int display_capacity;
	private ArrayList<DisplaySymbolBlock> display_queue;
	private int current_symbol_block;

	public SymbolDisplay() {
		setPreferredSize(new Dimension(Calculator.MIN_FRAME_WIDTH, 23));
		symbol_block_size = new Dimension(this.getPreferredSize().height, this.getPreferredSize().height);
		display_capacity = this.getPreferredSize().width / symbol_block_size.width;
		display_queue = new ArrayList<DisplaySymbolBlock>(display_capacity);
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
		for(var symbol_block: display_queue) {
			if(!symbol_block.getSymbol().isEmpty()) ++capacity;
		}
		return capacity;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int position = 0;
        for(var s: display_queue) 
        	s.draw(g
        		, position++
        		, this.getWidth() - (display_capacity - 1) * symbol_block_size.width
        		, 0);
    }
	
	public int addSymbol(String s) {
		//Add symbol to display stack, move other symbols to left
		if(current_symbol_block < 0) return current_symbol_block;
		display_queue.get(current_symbol_block).setSymbol(s);
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
			new_s = "E".repeat(display_capacity - new_s.length()) + new_s;
		for(int display_ind = 0, s_ind = 0
				; display_ind < display_capacity && s_ind < new_s.length()
				; ++display_ind, ++s_ind) {
			String cur_symbol = new_s.substring(s_ind, s_ind + 1);
			display_queue.get(display_ind).setSymbol(cur_symbol);
			if(display_ind == dot_pos) {
				if(display_queue.get(display_ind - 1).getSymbol().contentEquals("E"))
					display_queue.get(display_ind - 1).setSymbol("0");
				display_queue.get(display_ind).activateDot();
			}
		}
		repaint();
	}
	
	public void nullify() {
		for(var display_block: display_queue) { 
			display_block.setEmpty();
		}
		display_queue.get(display_capacity - 1).setSymbol("0");
		current_symbol_block = display_capacity - 1;
		repaint();
	}
	
	private void initDisplay() {
		for(int ind = 0; ind < display_capacity; ++ind) {
			display_queue.add(new DisplaySymbolBlock(symbol_block_size));
		}
		current_symbol_block = display_capacity;
	}
}
