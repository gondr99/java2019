package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import domain.CityVO;

public class FileJsonReader {
	public static void main(String[] args) {
		File file = new File("data/place.json");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			String json = "";
			while((line = br.readLine()) != null ) {
				json += line;
			}
			
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement jsonElem = parser.parse(json);
			JsonElement cityList = jsonElem.getAsJsonObject().get("city");
			
			CityVO[] voArray = gson.fromJson(cityList, CityVO[].class);
			
			List<CityVO> list = new ArrayList<CityVO>(Arrays.asList(voArray));
			
			list.sort((a, b) -> {
				return a.getPrice() - b.getPrice();
			});
			
			for(CityVO vo : list) {
				System.out.println(vo.getPlace_name() + ", " + vo.getPrice());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
