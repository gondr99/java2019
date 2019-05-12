package main;

import java.util.Scanner;

public class Subject4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int num = in.nextInt();
		
		int r = num / 100 + num / 10 % 10 + num % 10;
		System.out.println("결과 : " + r);
	}
}