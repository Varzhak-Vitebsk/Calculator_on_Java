package calculator.display;

import java.awt.Graphics;

final class LineAC extends Line {

	public LineAC(boolean use_main_color) {
		super(use_main_color);
	}

	@Override
	public void draw(Graphics g, int x, int y, int length) {
		g.setColor((useMainColor() ? main_color : back_color));
		//Middle line
		g.drawLine(x - Symbol.RIGHT_INDENT - length
				, y + Symbol.TOP_INDENT + Symbol.CORNER_INDENT
				, x - Symbol.RIGHT_INDENT - length
				, y + Symbol.TOP_INDENT + length - Symbol.CORNER_INDENT);
		//Inner line
		if(USE_INNER_LINE)
			g.drawLine(x - Symbol.RIGHT_INDENT - length + LINE_INDENT_X
				, y + Symbol.TOP_INDENT + Symbol.CORNER_INDENT + LINE_INDENT_Y
				, x - Symbol.RIGHT_INDENT - length +LINE_INDENT_X
				, y + Symbol.TOP_INDENT + length - Symbol.CORNER_INDENT - LINE_INDENT_Y);
	}
}
