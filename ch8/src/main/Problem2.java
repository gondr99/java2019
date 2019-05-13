package main;

import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("몇개의 숫자를 입력하시겠어요?");
		int n = in.nextInt();
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = i; j < i + n; j++) {
				System.out.print(arr[j % n] + " ");
			}
			System.out.println("");
		}
	
	}
}
