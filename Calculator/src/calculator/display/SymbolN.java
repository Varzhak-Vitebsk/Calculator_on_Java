package calculator.display;

import java.awt.Dimension;

final class SymbolN extends Symbol {

	public SymbolN(Dimension symbol_size, String symbol) {
		super(symbol_size, symbol);
		Line line = new LineAB(false);
		symbol_draw_map.add(line);
		line = new LineAC(true);
		symbol_draw_map.add(line);
		line = new LineBD(true);
		symbol_draw_map.add(line);
		line = new LineCE(true);
		symbol_draw_map.add(line);
		line = new LineDF(true);
		symbol_draw_map.add(line);
		line = new LineEF(false);
		symbol_draw_map.add(line);
		line = new LineAF(true);
		symbol_draw_map.add(line);
		
//		Line line = new LineAE(true);
//		symbol_draw_map.add(line);
//		line = new LineAF(true);
//		symbol_draw_map.add(line);
//		line = new LineBF(true);
//		symbol_draw_map.add(line);
	}

}
