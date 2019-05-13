package main;

import java.util.Scanner;

public class Problem1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("몇개를 입력하시겠습니까?");
		int n = in.nextInt();
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		for(int i=n-1; i>= 0; i--) {
			System.out.println(arr[i]);
		}
		
		in.close();
	}
}
