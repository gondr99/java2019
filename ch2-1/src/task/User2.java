package task;

import main.Calculator;

public class User2 extends Thread {
	private Calculator calc;

	public void setCalc(Calculator calc) {
		this.setName("User2쓰레드");
		this.calc = calc;
	}

	public void run() {
		calc.setMemory(400);
	}
}
