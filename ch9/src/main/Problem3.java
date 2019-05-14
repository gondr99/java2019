package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Problem3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> mList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect("http://www.y-y.hs.kr/lunch.list?ym=201904").get();
			Elements menus  = doc.select(".normal .tabContent > span > a");
			
			for(int i = 0; i < menus.size(); i++) {
				mList.add(menus.get(i).text());
			}
			
			System.out.println("좋아하는 메뉴를 입력하세요");
			String favor = in.nextLine();
			int cnt = 0;
			for(String menu : mList) {
				if(menu.contains(favor)) {
					cnt++;
				}
			}
			System.out.println("야호 이번달에는 " + favor + "가 " + cnt + "번 나와요");
		} catch (Exception e) {
			System.out.println("값을 가져오는 중 오류가 발생했습니다");
			e.printStackTrace();
		}
		in.close();

	}
}
