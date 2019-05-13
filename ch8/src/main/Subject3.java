package main;

import java.util.Scanner;

public class Subject3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] name = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		double[] score = new double[10];

		int idx = 0;
		for (int i = 0; i < name.length; i++) {
			System.out.println(name[i] + " 학생의 점수를 입력하세요");
			score[i] = in.nextDouble();
			if (score[i] > score[idx]) {
				idx = i;
			}
		}
		System.out.println("최대값 : " + score[idx] + ", 이름 : " + name[idx]);
		in.close();
	}
}
