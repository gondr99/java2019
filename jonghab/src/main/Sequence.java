package main;

import java.util.Scanner;

public class Sequence {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("N을 입력하세요");
		int N = in.nextInt();
		
		long sum = 0; 
		for(int i = 1; i <= N; i++) {
			long product = 1;
			for(int j = 1; j <= i; j++) {
				product *= j;
			}
			
			sum += product;
		}
		
		System.out.println(sum);
		in.close();
	}

}
