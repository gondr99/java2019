package net.gondr.tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.gondr.views.ScorePopupController;

public class App extends Application
{
	public static App app; //싱글톤 기법을 활용하기 위한 스태틱 변수
	
	public Game game = null;
	
	private Stage popupStage;
	private ScorePopupController popupController;
	
    public static void main( String[] args )
    {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			app = this; //스태틱 변수에 자기자신을 넣어서 싱글톤으로 활용함.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/net/gondr/views/Main.fxml"));
			AnchorPane anchorPane = (AnchorPane)loader.load();
			
			Scene scene = new Scene(anchorPane);
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
				if(game != null)
					game.keyHandler(e); //게임의 이벤트 핸들러로 넘겨준다.
			});
			
			FXMLLoader scorePopupLoader = new FXMLLoader();
			scorePopupLoader.setLocation(getClass().getResource("/net/gondr/views/ScorePopup.fxml"));
			
			popupStage = new Stage();
			popupStage.setTitle("게임기록");
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.initOwner(primaryStage);
			
			AnchorPane popup = scorePopupLoader.load();
			Scene popupScene = new Scene(popup);
			popupStage.setScene(popupScene);
			
			popupController = scorePopupLoader.getController();
			popupController.setDialogStage(popupStage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void openPopup(int score) {
		popupController.setScore(score);
		popupStage.show();
	}
}
