package layout;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vo.MatchReferenceDto;

public class MatchController {
	@FXML
	private ImageView imgChamp;
	@FXML
	private Label lblPos;
	@FXML
	private Label lblChamp;
	@FXML
	private Label lblPlatform;
	@FXML
	private Label lblSeason;
	@FXML
	private Label lblQueue;
	@FXML
	private Label lblRole;
	@FXML
	private Label lblTime;
	
public void loadData(MatchReferenceDto data) {
	lblPos.setText(data.getLane());
	//이름을 로드하도록 변경
	lblChamp.setText(data.getChampName());
	
	//챔피언데이터의 이미지 파일명을 이용해 이미지 로드
	Image champImage = new Image("http://ddragon.leagueoflegends.com/cdn/9.20.1/img/champion/" + data.getChampImage());
	imgChamp.setImage(champImage);
	
	lblPlatform.setText(data.getPlatformId());
	lblSeason.setText(data.getSeason().toString());
	lblQueue.setText(data.getQueue().toString());
	lblRole.setText(data.getRole());
	lblTime.setText(data.getTimestamp().toString());
}
	
}
