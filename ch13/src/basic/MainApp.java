package basic;

import soundable.Guitar;
import soundable.Piano;
import soundable.Soundable;

public class MainApp {	
	public static void main(String[] args) {
		printSound(new Guitar());
		printSound(new Piano());
	}
	
	private static void printSound(Soundable instance) {
		System.out.println(instance.sound());
	}
	
}