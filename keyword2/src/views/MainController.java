package views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {
	@FXML
	private TextField txtWord;
	
	@FXML
	private VBox newList;
	
	private String ci = "SKVGyHZK8CUen968PM9n"; //발급받은 클라이언트 아이디
	private String cs = "mPC7dJzDJi"; //발급받은 클라이언트 씨크릿키
	private String apiURL = "https://openapi.naver.com/v1/search/news.json";
	
	public void search() {
		String word = txtWord.getText();
		txtWord.setText("");
		System.out.println(word);
		try {
			word = URLEncoder.encode(word, "UTF-8");
			apiURL += "?query=" + word;
			
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", ci);
			con.setRequestProperty("X-Naver-Client-Secret", cs);
			
			int resCode = con.getResponseCode();
			BufferedReader br;
			if(resCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine = "";
			StringBuffer res = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			
			String json = res.toString();
			System.out.println(json);
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(json);
			
			JsonArray items = element.getAsJsonObject().get("items").getAsJsonArray();
			
			makeFXML(items);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void makeFXML(JsonArray items) throws Exception {
		newList.getChildren().clear();
		
		for(JsonElement item : items) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/NewsItem.fxml"));
			AnchorPane root = loader.load();
			NewsItemController nic = loader.getController();
			String title = item.getAsJsonObject().get("title").getAsString();
			String desc = item.getAsJsonObject().get("description").getAsString();
			String url = item.getAsJsonObject().get("link").getAsString();
			
			nic.setData(title, desc, url);
			newList.getChildren().add(root);
		}
	}
	
	public void onKeyPress(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			search();
		}
	}
}
