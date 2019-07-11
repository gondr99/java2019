package main;

public class Calculator {
	private int memory;
	
	public int getMemory() {
		return memory;
	}
	
	public synchronized void setMemory(int value) {
		this.memory = value;
		//매개값을 저장하고 고의적으로 2초 대기함.
		try {	Thread.sleep(2000);		} catch (Exception e) {}
		
		System.out.println(Thread.currentThread().getName() + " - 할당한 값 : " + value + ", 할당된 값 :" + this.memory);
	}
}
