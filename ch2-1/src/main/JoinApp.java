package main;

import task.SumThread;

public class JoinApp {
	public static void main(String[] args) {
		SumThread t = new SumThread();
		t.start();
		try {
			t.join(); //대기
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("쓰레드 대기중 오류 발생");
		}
		System.out.println("100000까지 합 : " + t.getSum());
	}
}
