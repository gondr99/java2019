package main;

import java.util.Scanner;

public class MaxValue {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("숫자를 입력하세요");
		int max = 0;
		while(true) {
			int num = in.nextInt();
			if(num < 0) break;
			if(num > max) max = num;
		}
		
		if(max == 0) {
			System.out.println("입력된 수가 없습니다.");
		}else {
			System.out.println("최대값 : " + max);
		}
		
		in.close();
	}
}
