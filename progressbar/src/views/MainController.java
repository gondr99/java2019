package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import task.ProgressTimer;

public class MainController {
	@FXML
	private ProgressBar bar;
	@FXML
	private Label lblStatus;
	
	private ProgressTimer pt;
	
	private boolean paused = false;
	
	@FXML
	private Button btnStart;
	@FXML
	private Button btnPause;
	@FXML
	private Button btnStop;
	
	@FXML
	private void initialize() {
		bar.setProgress(0);
		lblStatus.setText("0%");
		btnPause.setDisable(true);
		btnStop.setDisable(true);
	}
	
	public void quitApp() {
		if(pt != null) {
			pt.quit();
		}
	}
	
	public void start() {
		if(paused) {
			this.paused = false;
			pt.setPause(false);
		}else {
			if(pt != null) {
				pt.quit(); //종료시켜주고
			}
			pt = new ProgressTimer(bar, lblStatus);
			pt.start();
		}		
		btnPause.setDisable(false);
		btnStop.setDisable(false);
		btnStart.setDisable(true);
	}
	
	//일시정지
	public void pause() {
		this.paused = true;
		pt.setPause(this.paused);
		
		btnPause.setDisable(true);
		btnStop.setDisable(false);
		btnStart.setDisable(false);
	}
	
	//초기화
	public void stop() {
		if(pt != null) {
			pt.quit();
			pt = null;
		}
		bar.setProgress(0);
		lblStatus.setText("0%");
		this.paused = false;
		btnPause.setDisable(true);
		btnStop.setDisable(true);
		btnStart.setDisable(false);
	}
}
