package calculator.display;

import java.awt.Dimension;

final class Empty extends Symbol {

	public Empty(Dimension symbol_block_size, String symbol) {
		super(symbol_block_size, symbol);
		Line line = new Line0(false);
		symbol_draw_map.add(line);
		line = new Line1(false);
		symbol_draw_map.add(line);
		line = new Line2(false);
		symbol_draw_map.add(line);
		line = new Line3(false);
		symbol_draw_map.add(line);
		line = new Line4(false);
		symbol_draw_map.add(line);
		line = new Line5(false);
		symbol_draw_map.add(line);
		line = new Line6(false);
		symbol_draw_map.add(line);
		line = new Line7(false);
		symbol_draw_map.add(line);
		line = new Line8(false);
		symbol_draw_map.add(line);
	}

}
