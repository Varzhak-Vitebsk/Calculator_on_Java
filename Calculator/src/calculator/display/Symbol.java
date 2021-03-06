package calculator.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

//(0,0)	TOP INDENT
//	L					R
//	E	   --0-			I
//	F	  |  / |		G
//	T	  1 2  3		H
//	 	  |/   |		T
//	I	   --4-		
//	N	  |  / |		I
//	D	  5 6  7		N
//	E	  |/   |   _	D
//	N	   --8-   |_|	E
//	T					N
//		BOTTOM INDENT	T

abstract class Symbol {
	
	private String symbol;
	protected Dimension symbol_block_size;
	protected int symbol_width;
	protected ArrayList<Line> symbol_draw_map;
	static final int RIGHT_INDENT = 3;
	static final int LEFT_INDENT = 2;
	static final int TOP_INDENT = 2;
	static final int BOTTOM_INDENT = 2;
	static final int CORNER_INDENT = 1; //Empty space between lines
	static final int DOT_SIZE = 2;

	public Symbol(Dimension symbol_block_size, String symbol) {
		this.symbol_block_size = symbol_block_size;
		symbol_width = symbol_block_size.width - RIGHT_INDENT - LEFT_INDENT;
		symbol_draw_map = new ArrayList<Line>();
		this.symbol = symbol;
	}
	
	public void draw(Graphics g, int position, int right_top_x, int right_top_y, boolean is_dot_active) {
		for(Line line: symbol_draw_map)
			if(line.useMainColor()) {
				line.draw(g
					, right_top_x + symbol_block_size.width * position
					, right_top_y
					, symbol_width);
			}
			else continue;
//		g.setColor(is_dot_active ? Color.BLACK : Color.GRAY);
		if(is_dot_active) {
			g.setColor(Color.BLACK);
			g.fillRect(right_top_x + symbol_block_size.width * position - 1
				, right_top_y + TOP_INDENT + symbol_width * 2 - DOT_SIZE
				, DOT_SIZE, DOT_SIZE);
		}		
	}
	
	public String getSymbol() {
		return symbol;
	}
}
