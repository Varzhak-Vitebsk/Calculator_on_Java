package calculator.display;

import java.awt.Dimension;

final class SymbolEmpty extends Symbol {

	public SymbolEmpty(Dimension symbol_block_size, String symbol) {
		super(symbol_block_size, symbol);
		Line line = new LineAB(false);
		symbol_draw_map.add(line);
		line = new LineAC(false);
		symbol_draw_map.add(line);
		line = new LineBC(false);
		symbol_draw_map.add(line);
		line = new LineBD(false);
		symbol_draw_map.add(line);
		line = new LineCD(false);
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
