package main;

import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("첫번째 수를 입력하세요");
		double num1 = in.nextDouble();
		
		System.out.println("두번째 수를 입력하세요");
		double num2 = in.nextDouble();
		System.out.println("두수의 곱 : " + (num1 * num2));
		in.close();
	}
}
