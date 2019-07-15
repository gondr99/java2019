package views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController {
	@FXML
	private TextField txtWord;
	
	@FXML
	private VBox newsList;
	
	private String clientId = "I4efUNYceXBEveuDIDI1"; //발급받은 클라이언트 ID를 넣습니다.
	private String clientSecret = "vGoZaqhJIv"; //발급받은 클라이언트 Secret 를 넣습니다.
	private String apiURL = "https://openapi.naver.com/v1/search/news.json";
	
	public void search() {
		try {
			String word = txtWord.getText();
			if(word.isEmpty()) {
				return; //값이 비어있는 경우 동작 안함.
			}
			word = URLEncoder.encode(word, "UTF-8");
			apiURL += "?query=" + word;
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            //문자열을 받은후
            String json = response.toString();
            Gson gson = new Gson(); //Gson객체를 선언하고
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(json);
            JsonArray items = element.getAsJsonObject().get("items").getAsJsonArray();
            
            makeFXML(items);
            
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("API데이터를 가져오는 중 오류가 발생했습니다.");
		}		
	}
	
	private void makeFXML(JsonArray items) throws Exception{
		for(JsonElement item : items) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/NewsItem.fxml"));
			AnchorPane root = loader.load();
			NewsItemController nic = loader.getController();
			String title = item.getAsJsonObject().get("title").getAsString();
			String desc = item.getAsJsonObject().get("description").getAsString();
			String url = item.getAsJsonObject().get("link").getAsString();

			nic.setData(title, desc, url);
			newsList.getChildren().add(root);
		}
	}
}
