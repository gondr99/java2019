package net.gondr.tetris;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import net.gondr.domain.Block;
import net.gondr.domain.Player;

public class Game {
	private GraphicsContext gc;
	public Block[][] board;
	
	private double width;
	private double height;
	
	private AnimationTimer mainLoop; //메인루프
	private long before; //이전시간 기록변수
	
	private Player player;
	private double blockDownTime = 0;
	
	private int score = 0;
	private Label scoreLabel;
	private Canvas nextBlockCanvas;
	private GraphicsContext nbGc; //nextBlockGC
	
	private double nbWidth; //다음블록 캔버스 너비
	private double nbHeight; //다음블록 캔버스 높이 
	
	private boolean gameOver = false;
	private boolean first = true;
	
	public void gameStart() {
		gameOver = false;
		if(first) {
			mainLoop.start();
			first = false;
		}else {
			for(int i = 0; i < 20; i++) {
				for(int j = 0; j < 10; j++) {
					board[i][j].setData(false, Color.WHITE);
				}
			}
		}
		
	}
	
	public Game(Canvas canvas, Canvas nextBlockCanvas, Label scoreLabel) {
		this.nextBlockCanvas =nextBlockCanvas;
		this.nbWidth = nextBlockCanvas.getWidth();
		this.nbHeight = nextBlockCanvas.getHeight();
		this.nbGc = this.nextBlockCanvas.getGraphicsContext2D();
		this.scoreLabel = scoreLabel;
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
		
		//플레이어 모양 설정
		player = new Player(board);
		gameOver = true;

	}
	
	//업데이트 매서드
	public void update(double delta) {
		if(gameOver) return;
		
		blockDownTime += delta; //0.5초마다 블럭을 아래로 내린다. 이 수치는 난이도 조절기능에서 조절 가능.
		double limit = 0.5 - score / 100 < 0.1 ? 0.1 : 0.5 - score / 100;
		
		if(blockDownTime >= limit) {
			player.down();
			blockDownTime = 0;
		}
	}
	
	public void setGameOver() {
		gameOver = true;
	}
	
	public void checkLineStatus() {
		for(int i = 19; i >= 0; i--) { //맨 밑칸부터 검사하면서 올라간다.
			boolean clear = true;
			for(int j = 0; j < 10; j++) {
				if(!board[i][j].getFill()) {
					clear = false; //한칸이라도 비어 있다면 클리어되지 않은 것으로.
					break;
				}
			}
			if(clear) {//해당 줄이 꽉차 있다면
				score++;
				for(int j = 0; j < 10; j++) {
					board[i][j].setData(false, Color.WHITE); //해당 줄 지우고
				}
				//그 위로 한칸씩 다 내린다.
				for(int k = i - 1;  k >= 0; k--) {
					for(int j = 0; j < 10; j++) {
						board[k+1][j].copyData(board[k][j]);
					}
				}
				//첫번째 줄은 비운다.
				for(int j = 0; j < 10; j++) {
					board[0][j].setData(false, Color.WHITE);
				}
				i++;//그리고 한번더 이번줄을 검사하기 위해 i값을 하나 증가시켜 준다.
			}
		}
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
		
		scoreLabel.setText("Score: \n" + score);
		
		if(gameOver) {
			gc.setFont(new Font("Arial", 30));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("Game Over", width / 2, height / 2);
		}
	
		player.render(nbGc, nbWidth, nbHeight);
	}
	
	public void keyHandler(KeyEvent e) {
		if(gameOver) return;
		player.keyHandler(e);
	}
	
}
