package main;

import java.util.Scanner;

public class Problem5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("두 수를 입력하세요");
		
		int one = in.nextInt();
		int two = in.nextInt();
		if(one > two ) {
			System.out.println("큰 수 " + one + ", 작은 수 " + two);
		}else {
			System.out.println("큰 수 " + two + ", 작은 수 " + one);
		}
		
		in.close();
	}
}
