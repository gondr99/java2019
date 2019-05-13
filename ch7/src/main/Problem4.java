package main;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int n = in.nextInt();
		long factoSum = 1;
		
		for(int i = 2; i <= n; i++) {
			factoSum *= i;
		}
		System.out.println(factoSum);
		
		in.close();
	}
}
