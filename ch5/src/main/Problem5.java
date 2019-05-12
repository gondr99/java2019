package main;

import java.util.Scanner;

public class Problem5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int sum = 0;
		for(int i = 0; i < 5; i++) {
			int num = in.nextInt();
			if(num > 0) sum += num;
		}
		System.out.println("양수의 합 : " + sum);
		in.close();
	}
}
