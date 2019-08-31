package net.gondr.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.gondr.domain.ScoreVO;
import net.gondr.tetris.App;
import net.gondr.tetris.Game;

public class MainController {
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private Canvas nextBlockCanvas;
	
	@FXML
	private Label scoreLabel;
	
	@FXML
	private ListView<ScoreVO> topScoreList;
	
	private ObservableList<ScoreVO> list;
	
	@FXML
	public void initialize() {
		list = FXCollections.observableArrayList();
		topScoreList.setItems(list);
		App.app.game = new Game(gameCanvas, nextBlockCanvas, scoreLabel, list);
	}
	
	//게임 재시작 매서드
	public void startGame() {
		App.app.game.gameStart();
	}
	
	public void onKeyDown(KeyEvent e) {
		if(e.getCode() == KeyCode.SPACE) {
			e.consume();
			return;
		}
	}
	
}
