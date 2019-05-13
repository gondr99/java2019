package main;

import java.time.LocalDate;
import java.util.Scanner;

public class LastDay {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("년도와 월을 입력하세요");
		int y = in.nextInt();
		int m = in.nextInt();
		
		LocalDate date = LocalDate.of(y, m, 1);
		
		System.out.println(date.lengthOfMonth());
		in.close();
	}
}
