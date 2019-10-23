package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MainApp {
	public static void main(String[] args) {
		String ci = "SKVGyHZK8CUen968PM9n"; //클라이언트 아이디
		String cs = "mPC7dJzDJi"; //클라이언트 씨크릿
		String apiURL = "https://openapi.naver.com/v1/search/adult.json";
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("판별할 검색어를 입력하세요");
		String word = in.nextLine();
		
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
			
			String result = res.toString();
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			// { "adult": 1}
			int code = element.getAsJsonObject().get("adult").getAsInt();
			System.out.println(code);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("API 호출중 오류 발생");
		}
	}
}
