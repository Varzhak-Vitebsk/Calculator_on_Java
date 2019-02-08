package calculator;

public class Launcher {

	public static void main(String[] args) {
		try {
			Calculator calculator = new Calculator();
			calculator.start();
		}
		catch(RuntimeException e) {
			System.exit(1);
		}
	}

}
