package task;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CountThread extends Thread{
	private Label h;
	private Label m;
	private Label s;
	private long sec;
	
	public CountThread(Label h, Label m, Label s, long sec) {
		this.h = h;
		this.m = m;
		this.s = s;
		this.sec = sec;
	}
	
	private boolean status = true;
	private boolean first = true;
	private boolean quit = false;  //종료코드
	
	public void setQuit() {
		quit = true; //쓰레드 종료
	}
	
	@Override
	public void run() {
		while(!quit) {
			
			try {
				Thread.sleep(1000);
				if(!status) continue;
				
				sec++;				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("쓰레드 실행중 오류 발생");
			}
			
			Long hour = sec / 3600;
			Long min = sec % 3600 / 60;
			Long second = sec % 60; 
			
			Platform.runLater(()-> {
				h.setText(hour.toString());
				m.setText(min.toString());
				s.setText(second.toString());
			});
		}
	}
	
	public void stopCount() {
		status = false;
		sec = 0; //초기화
		Platform.runLater(()-> {
			h.setText("00");
			m.setText("00");
			s.setText("00");
		});
	}
	
	public void pauseCount() {
		status = false; //일시정지 상태
	}
	
	public void startCount() {
		if(first) {
			this.start(); //시작
			first = false;
		}else {
			status = true; //최초시작이 아닌경우 status만 변경
		}
	}
}
