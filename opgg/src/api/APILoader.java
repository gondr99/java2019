package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import vo.ChampDataVO;

public class APILoader {
	private String key = "RGAPI-9247cee3-93df-49d2-bbbc-1a20f867741c";
	private String prefix = "https://kr.api.riotgames.com/";
	
	private Map<Integer, ChampDataVO> champMap;
	
	public void initData() throws IOException{
		String url = "http://ddragon.leagueoflegends.com/cdn/9.20.1/data/ko_KR/champion.json";
		String data = loadData(url);
		
		champMap = new HashMap<>();
		Gson gson = new Gson();
		
		JsonObject obj = gson.fromJson(data,  JsonObject.class);
		
		JsonObject list = obj.get("data").getAsJsonObject();
		
		Set<Entry<String, JsonElement>> entrySet = list.entrySet();
		
		for(Entry<String, JsonElement> entry : entrySet) {
			JsonObject value = (JsonObject) entry.getValue();
			Integer key = value.get("key").getAsInt();
			String name = value.get("name").getAsString();
			String image = value.get("image").getAsJsonObject().get("full").getAsString();
			
			ChampDataVO temp = new ChampDataVO();
			temp.setKey(key);
			temp.setName(name);
			temp.setImage(image);
			
			champMap.put(key, temp);
		}
	}

	
	private String loadData(String urlString) throws IOException {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String data = "";
		while(true) {
			String recv = br.readLine();
			if(recv == null) {
				break;
			}
			data += recv;
		}
		br.close();
		return data;
	}
	
	public ChampDataVO getChampData(Integer key) {
		return champMap.get(key);
	}
	
	public String loadSummonerInfo(String name) throws IOException{
		String url = prefix + "lol/summoner/v4/summoners/by-name/" 
				+ URLEncoder.encode(name, "utf8").replace("+", "%20") 
				+ "?api_key=" + key; 
		return loadData(url);		
	}
	
	public String loadMatchData(String accountId) throws IOException {
		String url = prefix + "lol/match/v4/matchlists/by-account/" + accountId + "?beginIndex=0&endIndex=10&api_key=" + key;
		return loadData(url);
	}

}

