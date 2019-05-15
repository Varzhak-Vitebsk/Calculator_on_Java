package calculator.display;

import java.awt.Dimension;

final class SymbolFour extends Symbol {

	public SymbolFour(Dimension symbol_block_size) {
		super(symbol_block_size, Symbol.SYMBOL_FOUR);
		Line line = new LineAB(false);
		symbol_draw_map.add(line);
		line = new LineAC(true);
		symbol_draw_map.add(line);
		line = new LineBC(false);
		symbol_draw_map.add(line);
		line = new LineBD(true);
		symbol_draw_map.add(line);
		line = new LineCD(true);
		symbol_draw_map.add(line);
		line = new LineCE(false);
		symbol_draw_map.add(line);
		line = new LineDE(false);
		symbol_draw_map.add(line);
		line = new LineDF(true);
		symbol_draw_map.add(line);
		line = new LineEF(false);
		symbol_draw_map.add(line);
	}

}
