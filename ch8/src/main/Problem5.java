package main;

import java.util.Scanner;

public class Problem5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int[] numbers = new int[10];

		for (int i = 0; i < numbers.length; i++) {

			while (true) {
				numbers[i] = in.nextInt();// numbers 배열의 i번째에 값 입력받기
				boolean find = false;
				for (int j = 0; j < i; j++) {
					if (numbers[i] == numbers[j]) { // numbers배열의 j번째와 i번째가 같으면
						find = true;
						break;
					}
				}

				if (!find) {
					break;
				} else {
					System.out.println("중복입니다. 다시입력하세요");
				}
			} // while루프
		} // for루프

		System.out.println("입력된 수는 다음과 같습니다.");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
	}
}
