package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problem2_2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String[] arr = {"쯔위", "지효", "다현", "사나", "나연", "채영", "모모", "정연"};
		//이렇게 하면 배열이 바로 리스트로 변경된다.
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
		ArrayList<String> removeList = new ArrayList<>();
		while(true) {
			String str = in.next();
			if(str.equals("종료")) { //문자열은 이렇게 비교해야 한다.
				break;
			}
			
			if(list.remove(str)) {
				System.out.println("삭제되었습니다.");
				removeList.add(str);
			}else {
				System.out.println("존재하지 않습니다.");
			}
		}
		
		System.out.print("리스트 : ");
		for(String name : list) {
			System.out.print(name + " ");
		}
		System.out.println("");
		System.out.print("삭제 리스트 : ");
		for(String name : removeList) {
			System.out.print(name + " ");
		}
		in.close();
	}
}
