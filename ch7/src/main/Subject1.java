package main;

public class Subject1 {
	public static void main(String[] args) {
		echo("Hello! Y-Y Digital's software developer", 10);
	}
	
	public static void echo(String msg, int cnt){
		for(int i = 0; i < cnt; i++) {
			System.out.println(msg);
		}
	}
}
