package main;

import java.util.Scanner;

public class Subject2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		int n = in.nextInt();
		
		for(int i = 3; i <= n; i++) {
			if(i % 3 == 0) {
				System.out.println(i);
			}
		}
		in.close();
	}
}
