package main;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Subject2 {
	public static void main(String[] args) {
		String url = "http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=4113559000";
		
		ArrayList<Double> list = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements dataList = doc.select("data");
			
			for(Element data : dataList) {
				double temp = Double.parseDouble(data.selectFirst("temp").ownText()); //온도만 가져오기
				list.add(temp);
			}
			
			for(Double temp : list) {
				System.out.print(temp + "\t");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류 발생");
		}
	}
}
