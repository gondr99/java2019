package main;

import java.util.Scanner;

public class Subject3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("화씨 온도를 입력하세요");
		double f = in.nextDouble();
		
		double c = 5 * (f - 31) / 9;
		if(c >= 30) {
			System.out.println("더워요");
		}else if(c <= 10) {
			System.out.println("추워요");
		}
	}
}
