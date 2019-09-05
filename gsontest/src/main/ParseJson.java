package main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParseJson {
	public static void main(String[] args) {
		String json = "{name:\"최선한\", age:14, male:true}";
		
		JsonParser parser = new JsonParser();
		
		JsonElement jsonElem = parser.parse(json);
		JsonObject jsonObj = jsonElem.getAsJsonObject();
		System.out.println("name : " + jsonObj.get("name").getAsString());
		System.out.println("age : " + jsonObj.get("age").getAsInt());
		boolean male = jsonObj.get("male").getAsBoolean();
		
		if(male) {
			System.out.println("남성입니다.");
		}else {
			System.out.println("여성입니다.");
		}
		
	}
}
