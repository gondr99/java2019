package main;

import java.util.Scanner;

public class Subject1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("이름을 입력하세요");
		String name = in.next();
		System.out.println("입력하신 이름은 " + name);
		in.close();
	}
}
