package main;

public class Subject3 {
	public static void main(String[] args) {
		System.out.println(sum("안녕하세요", " 양영디지털 여러분") );
		System.out.println(sum(4, 5));
		System.out.println(sum(5, 7, 9));
	}
	
	public static String sum(String one, String two) {
		return one + two;
	}
	
	public static int sum (int one, int two) {
		return one + two;
	}
	
	public static int sum (int one, int two, int three) {
		return one + two + three;
	}
}
