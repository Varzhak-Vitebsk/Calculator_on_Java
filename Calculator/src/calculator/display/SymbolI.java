package calculator.display;

import java.awt.Dimension;

final class SymbolI extends Symbol {

	public SymbolI(Dimension symbol_size) {
		super(symbol_size, Symbol.SYMBOL_I);
		Line line = new LineAB(true);
		symbol_draw_map.add(line);
		line = new LineAC(false);
		symbol_draw_map.add(line);
		line = new LineBD(false);
		symbol_draw_map.add(line);
		line = new LineCE(false);
		symbol_draw_map.add(line);
		line = new LineDF(false);
		symbol_draw_map.add(line);
		line = new LineEF(true);
		symbol_draw_map.add(line);
		line = new LineGJ(true);
		symbol_draw_map.add(line);
	}

}
