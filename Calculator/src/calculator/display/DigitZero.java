package calculator.display;

import java.awt.Dimension;

final class DigitZero extends Symbol {

	public DigitZero(Dimension symbol_size, String symbol) {
		super(symbol_size, symbol);
		Line line = new Line0(true);
		symbol_draw_map.add(line);
		line = new Line1(true);
		symbol_draw_map.add(line);
		line = new Line2(false);
		symbol_draw_map.add(line);
		line = new Line3(true);
		symbol_draw_map.add(line);
		line = new Line4(false);
		symbol_draw_map.add(line);
		line = new Line5(true);
		symbol_draw_map.add(line);
		line = new Line6(false);
		symbol_draw_map.add(line);
		line = new Line7(true);
		symbol_draw_map.add(line);
		line = new Line8(true);
		symbol_draw_map.add(line);
	}

}
