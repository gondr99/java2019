package main;

import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("화씨 온도를 입력하세요");
		double f = in.nextDouble();
		
		double c = (5.0/9.0) * (f - 32);
		
		System.out.println("섭씨 : " + c);
	}
}
