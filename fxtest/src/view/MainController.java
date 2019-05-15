package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class MainController {
	@FXML
	private Button btn1;
	@FXML
	private Button btn2;
	@FXML
	private Button btn3;
	
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Label lbl3;
	
	@FXML
	private DatePicker datePicker;
	
	public void btn1Handle() {
		Integer sum = 0;
		for(int i = 1; i <= 1000; i++) {
			sum += i;
		}
		lbl1.setText(sum.toString());
	}
	
	public void btn2Handle() {
		String url = "http://www.y-y.hs.kr/lunch.view";
		
		LocalDate date = datePicker.getValue();
		
		if(date != null) {
			String day = date.format( DateTimeFormatter.ofPattern("yyyyMMdd") );
			url += "?date=" + day;
		}
		
		try {
			Document doc = Jsoup.connect(url).get();
			Element menuSpan = doc.selectFirst("#morning .menuName > span");
			
			if(menuSpan == null) {
				lbl2.setText("급식이 없습니다");
			}else {
				lbl2.setText(menuSpan.text());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			lbl2.setText("급식 가져오기 중 오류 발생");
		}
	}
	
	public void btn3Handle() {
		lbl3.setText("클릭");
	}
}
