package main;

import java.util.Scanner;

public class Problem7 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요");
		int num = in.nextInt();
		
		for(int i = 0; i < num; i++) {
			System.out.println("*****");
		}
		in.close();
	}
}
