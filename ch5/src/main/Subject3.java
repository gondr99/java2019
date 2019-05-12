package main;

import java.util.Scanner;

public class Subject3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int sum = 0;
		while(true) {	
			int num = in.nextInt();
			
			sum += num;
			System.out.println("합계 : " + sum);
			if(sum >= 100) {
				break;
			}
		}
		in.close();
	}
}
