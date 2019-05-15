package calculator.display;

import java.awt.Dimension;

final class SymbolTwo extends Symbol {

	public SymbolTwo(Dimension symbol_block_size) {
		super(symbol_block_size, Symbol.SYMBOL_TWO);
		Line line = new LineAB(true);
		symbol_draw_map.add(line);
		line = new LineAC(false);
		symbol_draw_map.add(line);
		line = new LineBC(false);
		symbol_draw_map.add(line);
		line = new LineBD(true);
		symbol_draw_map.add(line);
		line = new LineCD(true);
		symbol_draw_map.add(line);
		line = new LineCE(true);
		symbol_draw_map.add(line);
		line = new LineDE(false);
		symbol_draw_map.add(line);
		line = new LineDF(false);
		symbol_draw_map.add(line);
		line = new LineEF(true);
		symbol_draw_map.add(line);
	}
}
