package main;

import java.util.Scanner;

public class Subject2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("첫번째 숫자를 입력하세요");
		int num1 = in.nextInt();
		
		System.out.println("두번째 숫자를 입력하세요");
		int num2 = in.nextInt();
		
		System.out.println("합은 " + (num1 + num2));
		in.close();
	}
}
