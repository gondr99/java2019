package main;

import java.util.Scanner;

public class Double {

	public static void main(String[] args) {
		double sum = 0;
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			int n = in.nextInt();
			if (n % 2 == 0) {
				sum += n;
			}
		}
		System.out.println("결과" + sum);
		in.close();
	}
}
