package net.gondr.domain;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import net.gondr.tetris.App;

public class Player {
	private Point2D[][][] shape = new Point2D[7][][]; //총 7개의 테트리스 블럭이 존재함.
	private int nextBlock = 0; //다음 모양
	private int nextColor = 0; //다음 색상
	
	private int current = 0; //현재 모양
	private int rotate = 0; //현재 회전상태
	private int nowColor = 0;
	private Color[] colorSet = new Color[7]; //총 7개 색깔
	
	private Random rnd;
	
	private int x = 5;
	private int y = 2;
	
	private Block[][] board;
	
	public Player(Block[][] board) {
		this.board = board;
		//작대기
		shape[0] = new Point2D[2][];
		shape[0][0] = getPointArray("0,-1:0,0:0,1:0,2");
		shape[0][1] = getPointArray("-1,0:0,0:1,0:2,0");
		
		//네모
		shape[1] = new Point2D[1][];
		shape[1][0] = getPointArray("0,0:1,0:0,1:1,1");
		
		//ㄴ 모양
		shape[2] = new Point2D[4][];
		shape[2][0] = getPointArray("0,-2:0,-1:0,0:1,0");
		shape[2][1] = getPointArray("0,1:0,0:1,0:2,0");
		shape[2][2] = getPointArray("-1,0:0,0:0,1:0,2");
		shape[2][3] = getPointArray("-2,0:-1,0:0,0:0,-1");
		
		// 역 ㄴ 모양
		shape[3] = new Point2D[4][];
		shape[3][0] = getPointArray("0,-2:0,-1:0,0:-1,0");
		shape[3][1] = getPointArray("0,-1:0,0:1,0:2,0");
		shape[3][2] = getPointArray("0,0:1,0:0,1:0,2");
		shape[3][3] = getPointArray("-2,0:-1,0:0,0:0,1");
		
		// _┌━ 모양은 여러분이 만들어보세요.
		shape[4] = new Point2D[2][];
		shape[4][0] = getPointArray("-1,1:0,1:0,0:1,0");
		shape[4][1] = getPointArray("0,-1:0,0:1,0:1,1");
		
		// ─┐_ 모양은 여러분이 만들어보세요.
		shape[5] = new Point2D[2][];
		shape[5][0] = getPointArray("0,-1:0,0:1,0:1,1");
		shape[5][1] = getPointArray("-1,0:0,0:0,-1:1,-1");
		
		// ㅗ 모양은 여러분이 만들어보세요.
		shape[6] = new Point2D[4][];
		shape[6][0] = getPointArray("-1,0:0,0:0,-1:1,0");
		shape[6][1] = getPointArray("0,-1:0,0:0,1:1,0");
		shape[6][2] = getPointArray("-1,0:0,0:1,0:0,1");
		shape[6][3] = getPointArray("0,-1:0,0:-1,0:0,1");
		
		
		//색상 넣기
		colorSet[0] = Color.ALICEBLUE;
		colorSet[1] = Color.AQUAMARINE;
		colorSet[2] = Color.BEIGE;
		colorSet[3] = Color.BLUEVIOLET;
		colorSet[4] = Color.CORAL;
		colorSet[5] = Color.CRIMSON;
		colorSet[6] = Color.DODGERBLUE;
		
		rnd = new Random();
		nextBlock = rnd.nextInt(shape.length);
		nextColor = rnd.nextInt(colorSet.length);
		current = rnd.nextInt(shape.length);
		nowColor = rnd.nextInt(colorSet.length);
		
		draw(false);
	}
	
	private void draw(boolean remove) {
		for(int i = 0; i < shape[current][rotate].length; i++) {
			int bx = (int)shape[current][rotate][i].getX() + x;
			int by = (int)shape[current][rotate][i].getY() + y;
			board[by][bx].setData(!remove, colorSet[nowColor]); //제거나 색칠이냐
		}
	}
	
	public Point2D[] getPointArray(String pointStr) {
		// 0,-1:0,0:0,1:0,2 형식으로 데이터가 들어옴
		Point2D[] arr = new Point2D[4];
		String[] pointList = pointStr.split(":");
		for(int i = 0; i < pointList.length; i++) {
			String[] point = pointList[i].split(","); //컴마를 기준으로 나누고 
			double x = Double.parseDouble(point[0]);
			double y = Double.parseDouble(point[1]); //x,y좌표를 숫자로 변경해서
			arr[i] = new Point2D(x, y);
		}
		return arr;
	}
	
	public void keyHandler(KeyEvent e) {
		int dx = 0, dy = 0;
		boolean rot = false;
		if(e.getCode() == KeyCode.LEFT) {
			dx -= 1;
		}else if(e.getCode() == KeyCode.RIGHT) {
			dx += 1;
		}else if(e.getCode() == KeyCode.UP) {
			rot = true;			
		}
		
		move(dx, dy, rot); //이동
		
		//내려가는 로직은 별도로 관리
		if(e.getCode() == KeyCode.DOWN) {
			down();
		}else if(e.getCode() == KeyCode.SPACE) {
			while(!down()) {
				//do nothing
			}
		}
	}
	
	private void move(int dx, int dy, boolean rot) {
		draw(true); //지우고
		x += dx;
		y += dy;
		if(rot)
			rotate = (rotate + 1) % shape[current].length; //모양 갯수만큼만 증가
		if(!checkPossible()) {
			x -= dx;
			y -= dy;
			if(rot)  //회전되었었다면 회전도 원상복귀
				rotate = rotate - 1 < 0 ? shape[current].length - 1 : rotate -1; //하나 다시 빼주고			
		}
		draw(false);
	}
	
	public boolean down() {
		draw(true); //지우기
		y += 1;
		if(!checkPossible()) {
			y -=1;
			draw(false); //내려놓은 블럭 다시 그려주기
			App.app.game.checkLineStatus(); //블럭을 내린후에는 현재 라인상태를 체크하도록 함.
			getNextBlock();
			draw(false); //이동후 그려주기
			return true; //종료시에는 true
		}
		draw(false); //이동후 그려주기
		return false; //종료되지 않은경우 false
	}
	
	private void getNextBlock() {
		//다음블럭 가져와서 초기화
		current = nextBlock;
		nowColor = nextColor;
		nextBlock = rnd.nextInt(shape.length);
		nextColor = rnd.nextInt(colorSet.length);
		x = 5;
		y = 2;
		rotate = 0;
		
		if(!checkPossible()) {
			App.app.game.setGameOver(); //블럭이 생성되자마자 못움직이면 게임오버
		}
	}
	
	private boolean checkPossible() {
		for(int i = 0; i < shape[current][rotate].length; i++) {
			int bx = (int)shape[current][rotate][i].getX() + x;
			int by = (int)shape[current][rotate][i].getY() + y;
			if (bx < 0 || by < 0 || bx >= 10 || by >= 20 ) return false;
			
			if(board[by][bx].getFill()) return false; //이미 그곳에 블럭이 존재하면
		}
		return true;		
	}
	
	public void render(GraphicsContext gc, double width, double height) {
		//다음블럭 그리기
		
		Color color = colorSet[nextColor]; //다음블럭의 색상
		Point2D[] block = shape[nextBlock][0]; //다음블럭
		gc.clearRect(0, 0, width, height);
		double x = width / 2;
		double y = height / 2;
		double size = width / 4 - 10;
		if(nextBlock == 0) {
			y -= size;
		}
		
		for(int i = 0; i < block.length; i++) {
			double dx = x + block[i].getX() * size;
			double dy = y + block[i].getY() * size;
			gc.setFill(color.darker());
			gc.fillRoundRect(dx, dy, size, size, 4, 4);
			
			gc.setFill(color);
			gc.fillRoundRect(dx+2, dy+2, size - 4, size - 4, 4, 4);
		}
		
	}
}
