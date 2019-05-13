package main;

import java.util.Scanner;

public class Problem7 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int n = in.nextInt();
		
		boolean prime = true;
		for(int i = 2; i < n; i++) {
			if(n % i == 0) {
				prime = false;
				break;
			}
		}
		
		if(prime) {
			System.out.println("소수입니다.");
		}else {
			System.out.println("소수가 아닙니다.");
		}
		
		in.close();
	}
}
