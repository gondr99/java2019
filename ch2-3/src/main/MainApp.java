package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainApp {
	public static void main(String[] args) {
		String clientId = "I4efUNYceXBEveuDIDI1"; //발급받은 클라이언트 ID를 넣습니다.
		String clientSecret = "vGoZaqhJIv"; //발급받은 클라이언트 Secret 를 넣습니다.
		String apiURL = "https://openapi.naver.com/v1/search/news.json";
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("뉴스 검색어를 입력하세요.");
		String word = in.nextLine();  //검색할 뉴스 단어를 입력하세요.
		
		try {
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
            //ResponseVO 형태로 변환한다.
//            ResponseVO responseVO = gson.fromJson(json, ResponseVO.class);
//            
//            List<ItemVO> newsList = responseVO.getItems();
//            
//            for(ItemVO item : newsList) {
//            	System.out.println(item.getTitle() + "[" + item.getPubDate() + "]");
//            }
            //json 파서를 가져와서 파싱한다.
            JsonParser parser = new JsonParser();
            //파싱한 결과를 json element로 저장한다.
            JsonElement element = parser.parse(json);
            //json element를 object로 가져와서 거기서 total이라는 속서을 가져온뒤
            //해당 값을 정수형으로 가져온다
            JsonArray items = element.getAsJsonObject().get("items").getAsJsonArray();
            
            for(JsonElement item : items) {
            	String title = item.getAsJsonObject().get("title").getAsString();
            	System.out.println(title);
            }
            
            
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("API호출중 오류 발생");
		}
		in.close();
	}
}
