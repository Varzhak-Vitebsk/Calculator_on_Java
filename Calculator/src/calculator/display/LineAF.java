package calculator.display;

import java.awt.Graphics;

final class LineAF extends Line {

	public LineAF(boolean use_main_color) {
		super(use_main_color);
	}

	@Override
	public void draw(Graphics g, int x, int y, int length) {
		g.setColor((useMainColor() ? main_color : back_color));
		//Upper line
		if(USE_INNER_LINE)
			g.drawLine(x - Symbol.RIGHT_INDENT - length + Symbol.CORNER_INDENT/* * INNER_LINE_K*/ + LINE_INDENT_X
				, y + Symbol.TOP_INDENT + Symbol.CORNER_INDENT * INNER_LINE_K
				, x - Symbol.RIGHT_INDENT - Symbol.CORNER_INDENT/* * INNER_LINE_K*/ + LINE_INDENT_X
				, y + Symbol.TOP_INDENT + 2 * length - Symbol.CORNER_INDENT* INNER_LINE_K);		
		//Middle line
		g.drawLine(x - Symbol.RIGHT_INDENT - length + Symbol.CORNER_INDENT// * INNER_LINE_K
				, y + Symbol.TOP_INDENT + Symbol.CORNER_INDENT * INNER_LINE_K
				, x - Symbol.RIGHT_INDENT - Symbol.CORNER_INDENT// * INNER_LINE_K
				, y + Symbol.TOP_INDENT + 2 * length - Symbol.CORNER_INDENT * INNER_LINE_K);	
		//Inner line
//		if(USE_INNER_LINE)
//			g.drawLine(x - Symbol.RIGHT_INDENT - length + Symbol.CORNER_INDENT * INNER_LINE_K
//				, y + Symbol.TOP_INDENT + Symbol.CORNER_INDENT * INNER_LINE_K + LINE_INDENT_Y
//				, x - Symbol.RIGHT_INDENT - Symbol.CORNER_INDENT * INNER_LINE_K
//				, y + Symbol.TOP_INDENT + 2 * length - Symbol.CORNER_INDENT * INNER_LINE_K + LINE_INDENT_Y);
	}
}
