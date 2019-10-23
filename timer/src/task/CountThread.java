package task;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountThread extends Thread{
	private Label lblH;
	private Label lblM;
	private Label lblS;
	private long sec;
	
	private boolean status = true; //상태코드
	private boolean first = true; //최초 실행 코드
	private boolean quit = false; //종료코드
	
	public CountThread(Label h, Label m, Label s) {
		lblH = h;
		lblM = m;
		lblS = s;
		sec = 0;
	}
	
	public void setQuit() {
		quit = true;
	}
	
	@Override
	public void run() {
		while(!quit) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!status) continue;
			
			sec++;
			
			Long h = sec/3600;
			Long m = sec % 3600 / 60;
			Long s = sec % 60;
			
			Platform.runLater(() -> {
				String hh = "00" + h.toString();
				lblH.setText(hh.substring(hh.length() -2, hh.length()));
				String mm = "00" + m.toString();
				lblM.setText(mm.substring(mm.length() -2, mm.length()));
				String ss = "00" + s.toString();
				lblS.setText(ss.substring(ss.length() -2, ss.length()));
			});
			
		}
	}
	
	public void stopCount() {
		status = false;
		sec = 0;
		Platform.runLater(()->{
			lblH.setText("00");
			lblM.setText("00");
			lblS.setText("00");
		});
	}
	
	public void pauseCount() {
		status = false;
	}
	
	public void startCount() {
		if(first) {
			this.start();
			first = false;
		}else {
			status = true;
		}
	}
	
}
