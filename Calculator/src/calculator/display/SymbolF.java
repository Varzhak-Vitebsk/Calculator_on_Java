package calculator.display;

import java.awt.Dimension;

final class SymbolF extends Symbol {

	public SymbolF(Dimension symbol_size) {
		super(symbol_size, Symbol.SYMBOL_F);
		Line line = new LineAB(true);
		symbol_draw_map.add(line);
		line = new LineAC(true);
		symbol_draw_map.add(line);
		line = new LineBC(false);
		symbol_draw_map.add(line);
		line = new LineBD(false);
		symbol_draw_map.add(line);
		line = new LineCD(true);
		symbol_draw_map.add(line);
		line = new LineCE(true);
		symbol_draw_map.add(line);
		line = new LineDE(false);
		symbol_draw_map.add(line);
		line = new LineDF(false);
		symbol_draw_map.add(line);
		line = new LineEF(false);
		symbol_draw_map.add(line);
	}

}
