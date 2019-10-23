package main;

import task.NumThread;

public class Subject1 {
	public static void main(String[] args) {
		NumThread one = new NumThread(100);
		NumThread two = new NumThread(1000);
		NumThread three = new NumThread(10000);
		NumThread four = new NumThread(100000);
		
		one.start();
		two.start();
		three.start();
		four.start();
		
		try {
			one.join();
			two.join();
			three.join();
			four.join();
			long sum = 0;
			sum = one.getSum() + two.getSum() + three.getSum() + four.getSum();
			System.out.println(sum);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류 발생");
		}
	}
}
