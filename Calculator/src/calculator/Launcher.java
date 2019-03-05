package calculator;

import calculator.display.DisplayTest;

public class Launcher {

	public static void main(String[] args) {
		for (String arg : args) {
			switch (arg) {
			case ("-t"):
				try {
					DisplayTest dt = new DisplayTest();
					dt.start();
				} catch (RuntimeException e) {
					System.exit(1);
				}
				break;
			}
		}
		if (args.length == 0) {
			try {
				Calculator calculator = new Calculator();
				calculator.start();
			} catch (RuntimeException e) {
				System.exit(1);
			}
		}
	}

}
