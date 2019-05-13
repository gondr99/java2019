package main;

import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("괄호를 입력하세요");
		String str = in.nextLine();
		
		int open = 0, close = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				open++;
			}else if(str.charAt(i) == ')') {
				close++;
			}
		}
		System.out.println(open + ", " + close);
		in.close();
	}
}
