package calculator.display;

import java.awt.Dimension;

final class SymbolA extends Symbol {

	public SymbolA(Dimension symbol_size, String symbol) {
		super(symbol_size, symbol);
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
		line = new LineEF(false);
		symbol_draw_map.add(line);
		
//		Line line = new LineGE(true);
//		symbol_draw_map.add(line);
//		line = new LineGF(true);
//		symbol_draw_map.add(line);
//		line = new LineCD(true);
//		symbol_draw_map.add(line);
	}

}
