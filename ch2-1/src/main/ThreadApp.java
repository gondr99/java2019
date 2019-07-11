package main;

import task.User1;
import task.User2;

public class ThreadApp {
	public static void main(String[] args) {
		Calculator calc = new Calculator(); //공유될 객체
		
		User1 u1 = new User1();
		User2 u2 = new User2();
		
		//2개의 쓰레드에 모두 동일한 객체를 할당함.
		u1.setCalc(calc);
		u2.setCalc(calc);
		
		//2개의 객체를 실행함.
		u1.start();
		u2.start();
	}
}
