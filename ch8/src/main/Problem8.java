package main;

import java.util.Scanner;

public class Problem8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("지뢰판의 크기를 입력하세요");
		int n = in.nextInt();
		int[][] map = new int[n][n];
		
		System.out.println("지뢰의 갯수를 입력하세요");
		int cnt = in.nextInt();
		
		for(int i = 0; i < cnt; i++) {
			System.out.println("지뢰의 x, y 좌표 입력");
			int x = in.nextInt();
			int y = in.nextInt();
			map[y][x] = -1;
		}
		
		//지뢰에 따른 갯수 계산
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != -1) {
					map[i][j] = count(map, j, i);
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] < 0 ) {
					System.out.printf("%3s", "*");
				}else {
					System.out.printf("%3d", map[i][j]);
				}
			}
			System.out.println("");
		}
		
	}
	
	public static int count(int[][] map, int y, int x) {
		int cnt = 0;
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if( (i == 0 && j == 0) || x + i < 0 || x + i >= map.length || y + j < 0 || y + j >= map.length ) {
					continue;
				}
				cnt++;
			}
		}
		
		return cnt;
	}
	
}
