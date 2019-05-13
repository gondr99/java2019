package views;

import game.MainGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {
	@FXML
	private Button btnStart;
	@FXML
	private Canvas canvas;
	@FXML
	private Label lblFlag;
	
	private MainGame game;
	
	@FXML
	private void initialize() {
		//초기화 함수
		GraphicsContext gc = canvas.getGraphicsContext2D();
        
		game = new MainGame(gc);
		
		render();
	}
	
	public void render() {
		game.render();
	}
	
	public void clickHandle(MouseEvent e) {
		game.clickHandle(e, lblFlag);
	}
	
	public void restart() {
		game.initGame();
	}
}
