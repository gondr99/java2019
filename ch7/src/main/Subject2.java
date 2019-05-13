package main;

public class Subject2 {
	public static void main(String[] args) {
		//4층짜리 피라미드 찍기
		printPyramid(4);
		
		//7층짜리 피라미드 찍기
		printPyramid(7);
	}
	
	public static void printPyramid(int level) {
		for(int i = 0; i < level; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
	}
}
