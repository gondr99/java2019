package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Toon;

public class SaveToonList {
	public static void main(String[] args) {
		//네이버 요일별웹툰 메인페이지
		String url = "https://comic.naver.com/webtoon/weekday.nhn";
		
		ArrayList<Toon> toonList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements list = doc.select(".col > .col_inner li");
			
			for(Element item : list){
				Element aTag = item.selectFirst("li > a");
				Element img = item.selectFirst(".thumb img");
				String href = aTag.attr("href"); //href 속성 빼옴/
				int start = href.indexOf("titleId=");
				int end = href.indexOf("&");
				String toonId = href.substring(start + 8, end);
				String name = item.text();
				String src = img.attr("src"); //
				boolean find = false;
				for(Toon toon : toonList) {
					if(toon.toonName.equals(name)) {
						find = true;
						break;
					}
				}
				if(!find) {
					Toon toon = new Toon( name ,  toonId, src);
					toonList.add(toon);
				}
			}
			
			//save 로직
			File file = new File("toondata.txt");
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			for(Toon toon : toonList) {
				bw.write(toon.toonName + "#" + toon.toonId + "#" + toon.url);
				bw.newLine();
			}
			bw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("크롤링중 오류 발생");
		}
		
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("검색하고자 하는 웹툰의 이름을 입력하세요 ");
		String name = in.nextLine();
		
		for(Toon toon : toonList) {
			if(toon.checkContain(name)) {
				System.out.println(toon);
			}
		}
		
		in.close();
	}
}
