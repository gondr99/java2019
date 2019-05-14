package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Problem3_1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> mList = new ArrayList<>();
		try {
			System.out.println("년도와 월을 입력하세요");
			String ym = in.nextLine();
			Document doc = Jsoup.connect("http://www.y-y.hs.kr/lunch.list?ym=" + ym).get();
			Elements days  = doc.select(".normal");
			
			for(Element day : days) {
				Element menu = day.selectFirst(".tabContent a");
				if(menu != null) {
					Element num = day.selectFirst(".dayBox > span");
					String date = "0" + num.ownText();
					date = date.substring(date.length() - 2);
					mList.add(ym + date + " : " + menu.ownText());
				}
			}
			
			System.out.println("좋아하는 메뉴를 입력하세요");
			String favor = in.nextLine();
			ArrayList<String> favorList = new ArrayList<String>();
			
			for(String menu : mList) {
				if(menu.contains(favor)) {
					favorList.add(menu);
				}
			}
			if(favorList.size() > 0) {
				System.out.println("야호 이번달에는 " + favor + "가 " + favorList.size() + "번 나와요");
				for(String menu : favorList) {
					System.out.println(menu);
				}
			}else {
				System.out.println("이런 이번달에는 " + favor + "가 안나와요");
			}
			
		} catch (Exception e) {
			System.out.println("값을 가져오는 중 오류가 발생했습니다");
			e.printStackTrace();
		}
		in.close();
	}
}
