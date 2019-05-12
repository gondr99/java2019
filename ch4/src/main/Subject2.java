package main;

import java.util.Scanner;

public class Subject2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String name = in.nextLine();
		
		if(name.length() >= 10) {
			System.out.println("10글자 이상입니다.");
		}else {
			System.out.println("어서오세요 " + name + "님");
		}
	}
}
