package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem1_1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		System.out.println("숫자를 입력하세요");
		while(true) {
			int num = in.nextInt();
			if(num == 0) break;
			int i = 0;
			for(i = 0; i < list.size(); i++) {
				if(num < list.get(i)) {
					break;
				}
			}
			list.add(i, num);
		}
		
		for(Integer num : list) {
			System.out.printf("%3d", num);
		}
		in.close();
	}
}
