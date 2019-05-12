package main;

import java.util.Scanner;

public class Subject4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("밑변을 입력하세요");
		double h = in.nextDouble();
		System.out.println("높이를 입력하세요");
		double w = in.nextDouble();
		
		System.out.println("넓이는 " + ( h * w / 2));
		in.close();
	}
}
