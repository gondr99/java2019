package main;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("등수를 입력하세요");
		int rank = in.nextInt();
		
		if(rank == 1) {
			System.out.println("아주 잘했어요");
		}else if(rank > 1 && rank <= 3) {
			System.out.println("잘했네요");
		}else if(rank > 3 && rank <= 6) {
			System.out.println("보통입니다.");
		}else if(rank > 6 && rank <= 19) {
			System.out.println("노력이 필요합니다.");
		}else {
			System.out.println("잘못된 등수입니다.");
		}
		in.close();
	}
}
