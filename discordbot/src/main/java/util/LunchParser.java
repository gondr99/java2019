package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import domain.LunchVO;

public class LunchParser {
	public LunchVO getMenu(String date) {
		String url = "http://www.y-y.hs.kr/lunch.view?date=" + date;
		LunchVO lunchVO = new LunchVO();
		try {
			Document doc = Jsoup.connect(url).get();
			
			Element menuSpan = doc.selectFirst(".menuName > span");
			Element title = doc.selectFirst(".monthTit > a");
			
			lunchVO.setMenuString(menuSpan.text());
			lunchVO.setDate(title.text());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lunchVO;
	}
}
