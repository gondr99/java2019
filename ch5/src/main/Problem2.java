package main;

public class Problem2 {
	public static void main(String[] args) {
		int sum = 0;
		for(int i = 1; i <= 100; i++) {
			if(i % 2 == 0) {
				sum += i;
			}else if(i % 3 == 0) {
				sum -= i;
			}
		}
		System.out.println("결과 " + sum);
	}
}
