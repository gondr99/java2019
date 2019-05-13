package main;

import java.util.Random;
import java.util.Scanner;

public class Subject4 {
	public static void main(String[] args) {
		String[] when = {"오늘", "내일", "모레"};
		String[] where = {"옥상", "학교", "화장실", "우리집", "방송실", "기능반"};
		String[] who = {"트와이스","모모랜드", "교장선생님", "담임선생님", "EXO"};
		String[] what = {"춤을 춘다", "울부짖는다", "밥을 먹는다", "달리기를 한다", "공부한다"};
		String[] how = {"격렬하게", "의기소침하게", "그윽하게", "불평하며"};
		
		Scanner in = new Scanner(System.in);
		System.out.println("이름을 입력하세요");
		String name = in.next();
		Random rnd = new Random();
		
		//출력문을 넣으세요
		System.out.println(
				name + "는(은) " +
				when[rnd.nextInt(when.length)] + ", " +
				where[rnd.nextInt(where.length)] + "에서 " +
				who[rnd.nextInt(who.length)] + "와 함께  " +
				how[rnd.nextInt(how.length)] + " " +
				what[rnd.nextInt(what.length)]);
		in.close();
	}
	
	
}
