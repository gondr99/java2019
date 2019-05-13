package main;

import java.util.Scanner;

public class Nine2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("출력할 단수를 입력하세요");
		int n = in.nextInt();
		
		for(int i = 0; i <= n / 5; i++) { //i는 행을 의미
			int max = i * 5 + 5 < n ? i * 5 + 5 : n;
			for(int j = 1; j <= 9; j++) {
				for(int k = i * 5 + 1; k <= max; k++) {
					System.out.print(k + " X " + j + " = " + (k * j) + "\t");
				}
				System.out.println("");
			}
			System.out.println("");
		}
		in.close();
	}
	 
}
