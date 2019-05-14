package basic;

import enemy.Bomber;
import javafx.geometry.Point2D;

public class Subject3 {
	public static void main(String[] args) {
		Point2D[] pArr = new Point2D[] {
				new Point2D(30, 35),  
				new Point2D(35, 35),
				new Point2D(35, 40),
				new Point2D(40, 45),
		};
		//javafx의 Point2D객체가 임포트 안될때는 JRE System Library를 확인하세요
		
		Bomber bomber = new Bomber(10, 30, 30, 10, 10);
		
		for(Point2D point : pArr) {
			if(bomber.checkCollision(point.getX(), point.getY())) {
				System.out.println("총알 피격 : 4의 데미지");
				bomber.hit(4);
			}
		}
	}
}
