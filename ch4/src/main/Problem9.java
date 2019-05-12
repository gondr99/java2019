package main;

import java.util.Scanner;

public class Problem9 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("정수를 입력하세요");
		int n = in.nextInt();
		
		int h = n / 100;
		int t = n / 10 % 10;
		int o = n % 10;
		
		if( n >= 100) {
			System.out.println("" + o + t + h);
		}else if (n >= 10){
			System.out.println(t + o);
		}
		in.close();
	}
}
