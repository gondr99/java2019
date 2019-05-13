package main;

import java.util.Scanner;

public class Pyramid {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("피라미드 층수를 입력하세요");
		int N = in.nextInt();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N -i - 1; j++) {
				System.out.print(" ");
			}
			
			for(int j = 0; j < 2 * i + 1; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		in.close();
	}

}
