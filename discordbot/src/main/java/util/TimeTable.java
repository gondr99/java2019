package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DateTimeException;
import java.time.LocalDate;

public class TimeTable {
	public String getTime(String dateString, String classString) {
		String result = "";
		
		int classNumber = 1;
		try {
			classNumber = Integer.parseInt(classString);
		} catch (Exception e) {
			return "올바른 반번호가 아닙니다";
		}
		if(dateString.length() != 8) {
			return "날짜 형식이 일치하지 않습니다.";
		}else if(classNumber <= 0 || classNumber > 2) {
			return "해당하는 반은 존재하지 않습니다.";
		}
		
		int year = Integer.parseInt(dateString.substring(0, 4));
		int month = Integer.parseInt(dateString.substring(4, 6));
		int day = Integer.parseInt(dateString.substring(6, 8));
		
		LocalDate date = null;
		try {
			date = LocalDate.of(year, month, day);
		} catch (DateTimeException e) {
			return "올바르지 않은 날짜 형식입니다";
		}
		
		int dayOfWeek = date.getDayOfWeek().getValue(); //요일을 받아온다.
		
		String sql = "SELECT * FROM time WHERE class = ? AND day = ? ORDER BY period";
		
		Connection con = JDBCUtil.getConnection(); 
		if(con == null) {
			return "데이터베이스 연결에 문제가 있습니다.";
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, classNumber);
			pstmt.setInt(2, dayOfWeek);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int period = rs.getInt("period");
				String value = rs.getString("value");
				result += period + "교시 : " + value + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "SQL 구문 오류 발생";
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		
		return result;
	}
}
