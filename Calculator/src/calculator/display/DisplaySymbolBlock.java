package calculator.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

class DisplaySymbolBlock {
	
	private Dimension symbol_block_size;
	private HashMap<String, Symbol> symbols;
	private Symbol current_symbol;
	private boolean is_dot_active;

	public DisplaySymbolBlock(Dimension symbol_block_size) {
		this.symbol_block_size = symbol_block_size;
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
		symbols.put("E", new Empty(symbol_block_size, "E"));
		is_dot_active = false;
		setEmpty();
	}
	
	public void setEmpty() {
		current_symbol = symbols.get("E");
		deactivateDot();
	}
	
	public void setSymbol(String s) {
		current_symbol = symbols.get(s);
	}
	
	public String getSymbol() {
		return current_symbol.getSymbol();
	}
	
	public boolean isEmpty() {
		if(current_symbol.getSymbol().equals(symbols.get("E"))){
			return true;
		}
		else {
			return false;
		}
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
}
