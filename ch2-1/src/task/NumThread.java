package task;

public class NumThread extends Thread{
	private long sum = 0;
	private long target;
	
	public long getSum() {
		return sum;
	}
	
	public NumThread(long target) {
		this.target = target;
	}
	
	public void run() {
		for(int i = 1; i <= target; i++) {
			sum += i; 
		}
	}

}
