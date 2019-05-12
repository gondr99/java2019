package main;

import java.util.Scanner;

public class Subject1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int n = in.nextInt();
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			sum += n;
		}
		System.out.println("합계 : " + sum);
		in.close();
	}
}
