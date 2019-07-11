package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import task.CountThread;

public class MainController {
	@FXML
	private Label hour;
	
	@FXML
	private Label minute;
	
	@FXML
	private Label second;
	
	@FXML
	private Button startBtn;
	
	@FXML
	private Button stopBtn;
	
	private CountThread thread;
	private boolean status = false;
	
	@FXML
	private void initialize() {
		thread = new CountThread(hour, minute, second, 0);
	}
	
	public void stopThread() {
		thread.setQuit(); //쓰레드를 종료시키도록 함.
	}
	
	public void start() {
		if(!status) {
			thread.startCount();
			startBtn.setText("일시 정지");
			status = true;
		}else {
			thread.pauseCount();
			startBtn.setText("재시작");
			status = false;
		}
	}
	
	public void stop() {
		thread.stopCount();
		startBtn.setText("시작");
		status = false;
	}
}
