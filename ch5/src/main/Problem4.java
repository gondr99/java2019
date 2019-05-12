package main;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int sum = 0;
		int cnt = 0;
		System.out.println("숫자를 입력하세요. 0 입력시 종료");
		while(true) {
			int num = in.nextInt();
			if(num == 0) break;
			sum += num;
			cnt++;
		}
		
		System.out.println("합계 : " + sum + ", 평균 : " + (sum / (double)cnt));
		in.close();
	}
}
