package net.gondr.views;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
	public void initialize() {
		System.out.println("메인 레이아웃 초기화");
		App.app.game = new Game(gameCanvas, nextBlockCanvas, scoreLabel);
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
