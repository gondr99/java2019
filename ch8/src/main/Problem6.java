package main;

import java.util.Scanner;

public class Problem6 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("n을 입력하세요");
		int n = in.nextInt();
		int[][] arr = new int[n][n];

		int[] arr2 = new int[n * n];
		int ex = 1;
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = ex;
			ex++;
		}

		int push = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = arr2[push];
				push++;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}
