package calculator.display;

import java.awt.Dimension;

final class SymbolMinus extends Symbol {

	public SymbolMinus(Dimension symbol_size) {
		super(symbol_size, Symbol.SYMBOL_MINUS);
		Line line = new LineAB(false);
		symbol_draw_map.add(line);
		line = new LineAC(false);
		symbol_draw_map.add(line);
		line = new LineBC(false);
		symbol_draw_map.add(line);
		line = new LineBD(false);
		symbol_draw_map.add(line);
		line = new LineCD(true);
		symbol_draw_map.add(line);
		line = new LineCE(false);
		symbol_draw_map.add(line);
		line = new LineDE(false);
		symbol_draw_map.add(line);
		line = new LineDF(false);
		symbol_draw_map.add(line);
		line = new LineEF(false);
		symbol_draw_map.add(line);
	}

}
