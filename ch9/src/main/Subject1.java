package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Subject1 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		Scanner in = new Scanner(System.in);
		while (true) {
			int num = in.nextInt();
			if(num == 0 ) break;
			list.add(num);
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		in.close();
	}
}
