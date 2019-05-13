package main;

import java.util.Scanner;

public class Subject5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int[] numbers = new int[10];
		for(int i =0; i < numbers.length; i++) {
			numbers[i] = in.nextInt();
		}		
		int index = in.nextInt(); //몇번째 입력
		
		//이곳에 numbers[index]의 숫자가 몇번째로 큰 숫자인지 출력하는 코드
		int cnt = 1;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] > numbers[index]) {
				cnt++;
			}
		}
		System.out.println(numbers[index] + "는 " + cnt + "번째로 큽니다.");
	}
}
