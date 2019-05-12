package main;

import java.util.Scanner;

public class Subject5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("학생의 수를 입력하세요");
		int cntOfStudent = in.nextInt();
		System.out.println("연필의 수를 입력하세요");
		int cntOfPencil = in.nextInt();
		
		System.out.println("한명당 연필수 : " +  cntOfPencil / cntOfStudent);
		System.out.println("남는 연필수 : " + cntOfPencil % cntOfStudent);
	}
}
