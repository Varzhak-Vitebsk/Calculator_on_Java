package calculator.display;

import java.awt.Color;
import java.awt.Graphics;

abstract class Line {
	
	protected Color back_color = Color.GRAY, main_color = Color.BLACK;
	private boolean use_main_color;
	final static int LINE_INDENT_X = 1;
	final static int LINE_INDENT_Y = 1;
	final static int INNER_LINE_K = 3; //
	final static boolean USE_INNER_LINE = true;
	
	Line(boolean use_main_color){
		this.use_main_color = use_main_color;
	}

	abstract public void draw(Graphics g, int x, int y, int length);
	
	public boolean useMainColor() {
		return use_main_color;
	}
}
