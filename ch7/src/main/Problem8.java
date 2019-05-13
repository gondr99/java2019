package main;

import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("몸무게를 입력하세요");
		double w = in.nextDouble();
		System.out.println("키를 입력하세요");
		double h = in.nextDouble();
		
		double standard = (h - 100) * 0.9;
		double fat = (w - standard) * 100 / standard;
		
		if(fat <= 10) {
			System.out.println("정상입니다.");
		}else if( fat <= 20) {
			System.out.println("과체중");
		}else {
			System.out.println("비만");
		}
	}
}
