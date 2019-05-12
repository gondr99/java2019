package main;

import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("이름을 입력하세요");
		String name = in.next();
		
		System.out.println("나이를 입력하세요");
		int age = in.nextInt();
		
		System.out.println("이름 : " + name + ", 나이 : " + age);
		in.close();
	}
}
