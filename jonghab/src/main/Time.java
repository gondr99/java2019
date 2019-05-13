package main;

import java.util.Scanner;

public class Time {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("초를 입력하세요");
		int sec = in.nextInt();
		
		int h = sec / 3600;
		int m = sec / 60 % 60;
		int s = sec % 60;
		
		System.out.println(h + "시 " + m + "분 " + s + "초");
		
		in.close();
	}
}
