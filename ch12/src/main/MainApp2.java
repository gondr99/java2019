package main;

import enemy.Fighter;

public class MainApp2 {

	public static void main(String[] args) {
		Fighter f = new Fighter(3, 50, 50, 3, 4, 10);
		
		f.report();
		f.move();
		f.report();
		
		System.out.println(f.getAmmo());
		f.attack();
		System.out.println(f.getAmmo());
		
		f.spin(10,  10);
		f.report();
		f.spin(4,  3);
		f.move();
		f.spin(7,  2);
		f.report();
		f.move();
		
		f.attack();
		System.out.println(f.getAmmo());

	}

}
