package calculator.display;

import java.awt.Dimension;

final class DigitTwo extends Symbol {

	public DigitTwo(Dimension symbol_block_size, String symbol) {
		super(symbol_block_size, symbol);
		Line line = new Line0(true);
		symbol_draw_map.add(line);
		line = new Line1(false);
		symbol_draw_map.add(line);
		line = new Line2(false);
		symbol_draw_map.add(line);
		line = new Line3(true);
		symbol_draw_map.add(line);
		line = new Line4(true);
		symbol_draw_map.add(line);
		line = new Line5(true);
		symbol_draw_map.add(line);
		line = new Line6(false);
		symbol_draw_map.add(line);
		line = new Line7(false);
		symbol_draw_map.add(line);
		line = new Line8(true);
		symbol_draw_map.add(line);
	}

}
