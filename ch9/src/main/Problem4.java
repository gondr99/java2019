package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Problem4 {
	public static void main(String[] args) {
		try {
			String url = "https://news.google.com/rss?hl=ko&gl=KR";
			Document doc = Jsoup.connect(url).get();
			Elements list = doc.select("item");
			
			Scanner in = new Scanner(System.in);
			
			System.out.println("제외시키고 싶은 신문사를 입력하세요");
			List<String> hateList = new ArrayList<>();
			while(true) {
				String paper = in.nextLine();
				
				if(paper.isEmpty()) {break;}
				
				hateList.add(paper);
			}
			
			
			System.out.print("결과에서 ");
			for(String hate : hateList) {
				System.out.print(hate + " ");
			}
			System.out.println("는 거릅니다");
			for(int i = 0; i < list.size(); i++) {
				Element item = list.get(i);
				
				String title = item.selectFirst("title").text();
				
				boolean find = false;
				for(String hate : hateList) {
					if(title.contains(hate)) {
						find = true;
						break;
					}
				}
				
				if(!find) {
					String pubDate = item.selectFirst("pubDate").text();
					String link = item.selectFirst("link").text();
					System.out.println(title + "[" + pubDate + "]");
					System.out.println(link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
