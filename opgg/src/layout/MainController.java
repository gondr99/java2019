package layout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import api.APILoader;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import vo.ChampDataVO;
import vo.MatchReferenceDto;
import vo.SummonerVO;

public class MainController {
	@FXML
	private TextField txtSummoner;
	@FXML
	private Button btnLoad;
	
	@FXML
	private ImageView imgProfile;
	@FXML
	private Label lblName;
	@FXML
	private Label lblLevel;
	@FXML
	private Label lblDate;
	
	@FXML
	private VBox itemPane;
	
	private APILoader loader;
	
	public MainController() {
		loader = new APILoader();
		try {
			loader.initData();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("챔피언 데이터 로드중 오류 발생");
		}
	}
	
	public void loadSummonerData() {

		String name = txtSummoner.getText();
		try {
			String data = loader.loadSummonerInfo(name);
			
			Gson gson = new Gson();
			SummonerVO s = gson.fromJson(data, SummonerVO.class);
			
			lblName.setText("소환사 이름 " + s.getName());
			lblLevel.setText("소환사 레벨" + s.getSummonerLevel().toString());
			Date date = new Date(s.getRevisionDate());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			lblDate.setText("갱신일 : " + dateFormat.format(date));
			
			
			Image profileImg = new Image("http://ddragon.leagueoflegends.com/cdn/9.20.1/img/profileicon/" + s.getProfileIconId() + ".png");
			imgProfile.setImage(profileImg);
			
			getMatchData(s.getAccountId());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("해당 소환사는 존재하지 않습니다.");
		}
		
	}
	
	private void getMatchData(String accountId){
		try {
			String data = loader.loadMatchData(accountId);
			
			Gson gson = new Gson();
			
			JsonObject obj = gson.fromJson(data, JsonObject.class);
			JsonArray arr = obj.get("matches").getAsJsonArray();
			
			List<MatchReferenceDto> matchList = gson.fromJson(arr.toString(), new TypeToken<ArrayList<MatchReferenceDto>>(){}.getType());
			
			for(MatchReferenceDto match : matchList) {
				ChampDataVO champData = loader.getChampData(match.getChampion());
				match.setChampImage(champData.getImage());
				match.setChampName(champData.getName());
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("/layout/MatchLayout.fxml"));
				AnchorPane matchLayout = (AnchorPane) loader.load();
				MatchController mc = loader.getController();
				
				mc.loadData(match);
				
				itemPane.getChildren().add(matchLayout);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("전적정보 갱신중 오류 발생");
		}
		
	}

	
	public void onKeyPress(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			loadSummonerData();
		}
	}
}

