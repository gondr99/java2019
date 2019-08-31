package task;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressTimer extends Thread {
	private ProgressBar bar;
	private Label lblStatus;
	
	private double current = 0.0;
	private boolean pause = false;
	private boolean stop = false;
	
	public ProgressTimer(ProgressBar bar, Label lblStatus) {
		this.bar = bar;
		this.lblStatus = lblStatus;
	}
	
	public void quit() {
		stop = true;
	}
	
	public void setPause(boolean value) {
		this.pause = value;
	}
	
	@Override
	public void run() {
		while(this.current < 1 && !stop) {			
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println("프로그레스바 실행중 오류 발생");
			}
			if(pause) {
				continue; 
			}
			
			this.current += 0.001;
			
			if(!stop) {
				Platform.runLater(()->{
					this.bar.setProgress(current);
					this.lblStatus.setText( Math.round(current * 1000) / 10.0 + "%" );
				});
			}
		}
	}
}
