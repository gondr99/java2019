package main;

public class Problem3 {
	public static void main(String[] args) {
		int ten = factorial(10);
		int hund = factorial(15);
		
		System.out.println("10! 은" + ten);
		System.out.println("15! 은" + hund);

	}
	
	public static int factorial(int n) {
		int factoSum = 1;
		
		for(int i = 2; i <= n; i++) {
			factoSum *= i;
		}
		
		return factoSum;
	}
}
