package calculator.display;

import java.awt.Graphics;

class DisplaySymbolBlock {
	
	private Symbol current_symbol;
	private boolean is_dot_active;

	public DisplaySymbolBlock() {
		is_dot_active = false;
	}
	
	public void setSymbol(Symbol s) {
		current_symbol = s;
	}
	
	public Symbol getSymbolObj() {
		return current_symbol;
	}
	
	public void draw(Graphics g, int position, int right_top_x, int right_top_y) {
		current_symbol.draw(g, position, right_top_x, right_top_y, is_dot_active);		
	}
	
	public void activateDot() {
		is_dot_active = true;
	}
	
	public void deactivateDot() {
		is_dot_active = false;
	}
	
	public boolean hasDot() {
		return is_dot_active;
	}
	
	public void copy(DisplaySymbolBlock symbol_block) {
		current_symbol = symbol_block.getSymbolObj();
		is_dot_active = symbol_block.hasDot();
	}
}
