package main;

import java.util.Scanner;

public class Subject1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] name = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
		double[] score = new double[10];
		
		for(int i = 0; i < name.length; i++) {
			System.out.println(name[i] + " 학생의 점수를 입력하세요");
			score[i] = in.nextDouble();
		}
		
		for(int i = 0; i < score.length; i++) {
			System.out.println(score[i]);
		}
		
		in.close();
	}
}
