package main;

import java.util.Scanner;

public class SumAver {
	public static void main(String[] args) {
		double sum = 0;
		
		Scanner in = new Scanner(System.in);
		
		int cnt = 0;
		while(true) {
			int n = in.nextInt();
			if(n == 0) {
				break;
			}
			sum += n;
			cnt++;
		}
		if(cnt > 0) {
			System.out.println("합계 : " + sum + ", 평균 : " + (sum / cnt));
		}else {
			System.out.println("합계 : 0.0, 평균 : 0.0");
		}
		
		in.close();
	}
}
