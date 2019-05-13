package main;

import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int n = in.nextInt();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		in.close();
	}
}
