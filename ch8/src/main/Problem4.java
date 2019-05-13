package main;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		char[] alphas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int[] cnts = new int[alphas.length];
		
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if( c - 'a' >= 0 && c - 'a' < alphas.length) {
				cnts[c-'a']++;
			}
		}
		
		for(int i = 0; i < cnts.length; i++) {
			if(cnts[i] > 0) {
				System.out.println(alphas[i] + " : " + cnts[i] + "개");
			}
		}
		
		in.close();
	}
}
