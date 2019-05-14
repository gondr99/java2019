package game;

import java.util.Random;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import util.AppUtil;

public class MainGame {
	private GraphicsContext gc;
	private int gap = 5;
	private int size = 25;
	
	private Integer[][] board;
	private int[][] reveal;
	
	private boolean debug = false;
	
	private int mineCnt = 10;//초기 지뢰 갯수
	private int flagCnt = 0; //초기 깃발 갯수
	
	private boolean gameover = true; //게임오버 상태변수
	
	long startTime = 0;
	
	public MainGame(GraphicsContext gc) {
		this.gc = gc;
		board = new Integer[10][10];
		reveal = new int[10][10];
	}
	
	public void initGame() {
		int[] minePos = new int[100];
		for(int i = 0; i < 100; i++) {
			minePos[i] = i; 
		}
		//초기화
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
				reveal[i][j] = MineStatus.LOCKED;
			}
		}
		//지뢰배치
		Random rnd = new Random();
		for(int i = 0; i < mineCnt; i++) {
			int idx = rnd.nextInt(100 - i);
			int pos = minePos[idx];
			minePos[idx] = minePos[100-i -1]; //기존값은 다시 안나오게 덮어씌우고
			
			int y = pos / 10;
			int x = pos % 10;
			
			board[x][y] = -1; //-1은 지뢰를 뜻함.
		}
		
		//숫자계산
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == -1) continue;
				board[i][j] = checkCount(i, j);
			}
		}
		
		gameover = false;
		flagCnt = 0;
		
		startTime = System.currentTimeMillis(); //시작시간
		render();
	}
	
	private int checkCount(int y, int x) {
		int cnt = 0;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(x + j < 0 || x+ j >= 10 || y + i < 0 || y + i >= 10 || ( i == 0 && j == 0)) continue; //배열경계를 나갈경우
				if(board[y + i][x + j] == -1) cnt++;
			}
		}
		return cnt;
	}
	
	public void render() {
		gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
		gc.setStroke(Color.rgb(250, 250, 250));
		for (int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				gc.setFill(Color.rgb(86, 98, 112));
				int x = gap + j* gap + j * size;
				int y = gap + i * gap + i * size;
				gc.fillRect(x, y, size, size);
				gc.setFill(Color.rgb(165,  147,  224));
				gc.fillRect(x + 2, y + 2, size - 4, size -4);
				
				if(debug) {
					gc.strokeText(board[i][j].toString(), x + size / 2, y + size / 2);
				}else if(reveal[i][j] == MineStatus.REVEAL) {
					gc.strokeText(board[i][j].toString(), x + size / 2, y + size / 2);
					
				}else if(reveal[i][j] == MineStatus.FLAGED) {
					gc.setFill(Color.rgb(40,89,67));
					gc.fillRoundRect(x + 4, y + 4, size - 8, size -8, 4, 4);
				}
			}
		}
	}
	
	public void clickHandle(MouseEvent e, Label lblFlag) {
		if(gameover) return;
		double mouseX = e.getX(); //마우스 클릭된 좌표를 알아낸다.
		double mouseY = e.getY();
		
		int blockSize = gap + size;
		if(mouseX % blockSize < gap || mouseY % blockSize < gap ) {
			return; //경계 클릭은 아무 처리도 하지 않는다.
		}
		
		int x = (int)(mouseX / (gap + size));
		int y = (int)(mouseY / (gap + size));
		
		if(x > 10 || y > 10) {
			return; //게임판을 벗어난 클릭은 처리하지 않는다.
		}
		
		MouseButton btn = e.getButton();
		if(btn == MouseButton.SECONDARY) {
			rightClick(y, x, lblFlag);
		}else {
			leftClick(y, x);
		}
		render();
	}
	
	private void leftClick(int y, int x) {
		if(reveal[y][x] == MineStatus.REVEAL || reveal[y][x] == MineStatus.FLAGED) {
			return; //이미 밝혀진 것은 리턴
		}
		
		reveal[y][x] = MineStatus.REVEAL;
		
		if(board[y][x] == 0) {
			reveal(y, x);
		}
		
		if( board[y][x] == -1) {
			AppUtil.alert("게임 오버!", null);
			gameover = true;
		}
		
	}
	
	private void reveal(int y, int x) {
		reveal[y][x] = MineStatus.REVEAL;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(x + j < 0 || x+ j >= 10 || y + i < 0 || y + i >= 10 || ( i == 0 && j == 0)) continue; //배열경계를 나갈경우
				if(board[y + i][x + j] == 0 && reveal[y+i][x+j] == MineStatus.LOCKED) {
					reveal(y+i, x+j);
				}else if(board[y + i][x + j] != 0 && reveal[y+i][x+j] == MineStatus.LOCKED) {
					reveal[y + i][x + j] = MineStatus.REVEAL;
				}
			}
		}
		
	}
	
	private void rightClick(int y, int x, Label lbl) {
		if(reveal[y][x] == MineStatus.FLAGED) {
			reveal[y][x] = MineStatus.LOCKED;
			flagCnt--;
			lbl.setText("남은 깃발 : " + (mineCnt - flagCnt));
			return;
		}
		
		if(flagCnt >= mineCnt) {
			AppUtil.alert("설정할 수 있는 깃발의 최대수를 초과하였습니다.", null);
			return;
		}
		
		reveal[y][x] = MineStatus.FLAGED;
		flagCnt++; 
		lbl.setText("남은 깃발 : " + (mineCnt - flagCnt));
		if(checkGame()) {
			AppUtil.alert("게임 클리어!", null);
			gameover = true;
			long endTime = System.currentTimeMillis();
			lbl.setText("걸린시간 : " + Math.floor((endTime - startTime) / 1000 ));
		}
	}
	
	private boolean checkGame() {
		int cnt = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == -1 && reveal[i][j] == MineStatus.FLAGED) {
					cnt++;
				}
			}
		}
		return cnt == mineCnt; //같으면 게임 클리어 
	}
	
	
	
}
