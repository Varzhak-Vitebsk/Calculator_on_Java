package calculator.display;

import java.awt.Dimension;

final class SymbolSix extends Symbol {

	public SymbolSix(Dimension symbol_block_size, String symbol) {
		super(symbol_block_size, symbol);
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
		line = new LineDF(true);
		symbol_draw_map.add(line);
		line = new LineEF(true);
		symbol_draw_map.add(line);
	}

}
