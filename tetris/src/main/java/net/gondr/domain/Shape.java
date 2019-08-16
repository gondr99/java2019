package net.gondr.domain;

import javafx.geometry.Point2D;

public class Shape {
	private Point2D[][][] shape = new Point2D[7][][]; //총 7개의 테트리스 블럭이 존재함.
	
	public Shape() {
		//작대기
		shape[0] = new Point2D[2][];
		shape[0][0] = getPointArray("0,-1:0,0:0,1:0,2");
		shape[0][1] = getPointArray("-1,0:0,0:1,0:2,0");
		
		//네모
		shape[1] = new Point2D[1][];
		shape[1][0] = getPointArray("0,0:1,0:0,1:1,1");
		
		//ㄴ 모양
		shape[2] = new Point2D[4][];
		shape[2][0] = getPointArray("0,-2:0,-1:0,0:1:0");
		shape[2][1] = getPointArray("-2,0:-1,0:0,0:0,-1");
		shape[2][2] = getPointArray("0,-2:0,-1:0,0:-1,0");
		shape[2][3] = getPointArray("0,1:0,0:1,0:2,0");
		
		// 역 ㄴ 모양
		shape[2] = new Point2D[4][];
		shape[2][0] = getPointArray("0,-2:0,-1:0,0:1:0");
		shape[2][1] = getPointArray("-2,0:-1,0:0,0:0,-1");
		shape[2][2] = getPointArray("0,-2:0,-1:0,0:-1,0");
		shape[2][3] = getPointArray("0,1:0,0:1,0:2,0");
		
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
}
