package task;

public class SumThread extends Thread{
	private long sum;
	
	public long getSum() {
		return sum;
	}
	
	public void setSum(long sum) {
		this.sum = sum;
	}
	
	public void run() {
		for(int i = 1; i <= 100000; i++) {
			sum += i; //1부터 100000까지 더함
		}
	}
}
