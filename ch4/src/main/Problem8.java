package main;

import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		double num = in.nextDouble();
		
		if(num % 1 == 0) {
			System.out.println("정수입니다.");
		}else {
			System.out.println("실수입니다.");
		}
		in.close();
	}
}
