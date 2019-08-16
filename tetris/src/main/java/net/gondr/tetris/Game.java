package net.gondr.tetris;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.gondr.domain.Block;

public class Game {
	private Canvas canvas;
	private GraphicsContext gc;
	private Block[][] board;
	
	private double width;
	private double height;
	
	private AnimationTimer mainLoop; //메인루프
	private long before; //이전시간 기록변수
	
	public Game(Canvas canvas) {
		//캔버스의 너비와 높이를 가져온다.
		width = canvas.getWidth();
		height = canvas.getHeight();
		
		double size = (width - 4) / 10;
		
		board = new Block[20][10]; //게임 판 만들고
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 10; j++) {
				board[i][j] = new Block(j * size + 2, i * size + 2, size);
			}
		}
		
		this.canvas = canvas; //캔버스와 컨텍스트를 저장하기.
		this.gc = canvas.getGraphicsContext2D();
		
		mainLoop = new AnimationTimer() {
			@Override
			public void handle(long now) { //now는 나노초 단위로 들어옴.
				update( (now - before) / 1000000000d );
				before = now;
				render();
			}
		};
		
		before = System.nanoTime();
		
		mainLoop.start();
		
		//test 코드
		board[15][1].setData(true, Color.BEIGE);
		board[15][2].setData(true, Color.BEIGE);
		board[15][3].setData(true, Color.BEIGE);
		board[15][4].setData(true, Color.BEIGE);
	}
	
	//업데이트 매서드
	public void update(double delta) {
		
	}
	
	//렌더 메서드
	public void render() {
		//스테이지 그리기
		gc.clearRect(0, 0, width, height); //전부 지우고 새로 그리기
		gc.setStroke(Color.rgb(0, 0, 0)); //검은색으로 외곽선 그리고
		gc.setLineWidth(2);
		gc.strokeRect(0, 0, width, height);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 10; j++) {
				board[i][j].render(gc);
			}
		}
	}
	
}
