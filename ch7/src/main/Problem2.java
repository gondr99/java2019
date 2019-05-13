package main;

public class Problem2 {
	public static void main(String[] args) {
		oddPrint(15);
		oddPrint(10);
	}
	
	public static void oddPrint(int x) {
		if(x % 2 == 1) {
			System.out.println(x);
		}
	}
}
