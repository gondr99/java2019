package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		String clientId = "I4efUNYceXBEveuDIDI1"; //발급받은 클라이언트 ID를 넣습니다.
		String clientSecret = "vGoZaqhJIv"; //발급받은 클라이언트 Secret 를 넣습니다.
		String apiURL = "https://openapi.naver.com/v1/search/news.json";
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("판별할 검색어를 입력하세요");
		String word = in.nextLine();  //성인검색어인지를 판별할 검색어
		
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
            if(responseCode==200) { // 정상 호출
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
            System.out.println(response.toString());
            
//            String json = response.toString();
//            
//            int idx = json.lastIndexOf(":");
//            json = json.substring(idx + 3, json.length() - 2);
//            
//            int result = Integer.parseInt(json);
//            System.out.println(result);
					
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("API호출중 오류 발생");
		}
		in.close();
	}
}
