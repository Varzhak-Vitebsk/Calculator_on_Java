package calculator.display;

import java.awt.Dimension;

final class SymbolEight extends Symbol {

	public SymbolEight(Dimension symbol_block_size) {
		super(symbol_block_size, Symbol.SYMBOL_EIGHT);
		Line line = new LineAB(true);
		symbol_draw_map.add(line);
		line = new LineAC(true);
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
		line = new LineDF(true);
		symbol_draw_map.add(line);
		line = new LineEF(true);
		symbol_draw_map.add(line);
	}

}
