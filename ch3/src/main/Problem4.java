package main;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("원의 반지름을 입력하세요");
		double r = in.nextDouble();
		
		System.out.println("넓이는 " + r * r * Math.PI);
		in.close();
		
	}
}
