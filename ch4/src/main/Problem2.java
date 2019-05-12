package main;

import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("이름을 입력하세요");
		String name = in.nextLine();
		if(name.length() >= 5) {
			System.out.println("너무 깁니다.");
		}else {
			System.out.println("어서오세요 " + name + "님");
		}
		in.close();
	}
}
