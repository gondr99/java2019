package main;

import task.BeepTask;

public class MainApp {

	public static void main(String[] args) {
		
		Thread t = new Thread(new BeepTask());
		System.out.println(t.getName());
		t.start();
		
		Thread t2 = Thread.currentThread();
		System.out.println(t2.getName());
		for(int i = 0; i < 5; i++) {
			System.out.println("띠리링");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				//do nothing
			}
		}

	}
}
