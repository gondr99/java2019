package views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.UserVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.MainApp;
import util.JDBCUtil;
import util.Util;

public class MainController extends MasterController{
	@FXML
	private Button btnPrev;
	@FXML
	private Button btnNext;
	@FXML
	private Label lblDate;
	@FXML
	private Label lblDay;
	
	@FXML
	private Label loginInfo;
	
	
	@FXML
	private GridPane gridCalendar;
	
	private UserVO user = null;
	
	private List<DayController> dayList;
	private Map<String, String> dayOfWeek = new HashMap<>();
	
	private YearMonth currentYM; //현재의 년월을 저장하는 변수
	@FXML
	private void initialize() {
		dayList = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) { //달력의 행
			for(int j = 0; j < 7; j++) { //달력의 열
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/views/DayLayout.fxml"));
				try {
					AnchorPane ap = loader.load();
					gridCalendar.add(ap, j, i);
					DayController dc = loader.getController();
					dc.setRoot(ap);
					dayList.add(dc);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.printf("j : %d, i : %d 번째 그리는 중 오류 발생\n", j, i);
					Util.showAlert("에러", "달력칸을 그리는 중 오류가 발생", AlertType.ERROR);
				}
			}
		}
		
		String[] engDay = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
		String[] korDay = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
				
		for(int i = 0; i < engDay.length; i++) {
			dayOfWeek.put(engDay[i], korDay[i]); 
		}
		
	}
	
	public void setToday(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		lblDate.setText(date.format(dtf));
		lblDay.setText(dayOfWeek.get(date.getDayOfWeek().toString()));
	}
	
	public void loadMonthData(YearMonth ym) {
		LocalDate calendarDate = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1); //해당 년월의 1일을 가져온다.
		while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) { //일요일이 아닐때까지 하루씩 빼간다.
			calendarDate = calendarDate.minusDays(1); //하루씩 감소
		}
		
		//여기까지 오면 해당주간의 첫째날이 오게된다. 여기서부터 캘린더를 그리기 시작한다.
		
		//해당 월의 첫째날과 마지막 날의 날짜를 구한다.
		LocalDate first = LocalDate.of(ym.getYear(), ym.getMonthValue(), 1);
		LocalDate last = LocalDate.of(ym.getYear(), ym.getMonthValue() + 1, 1).minusDays(1);
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT date, COUNT(*) AS cnt FROM diary_todos WHERE owner = ? AND date BETWEEN ? AND ? GROUP BY date";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setDate(2, Date.valueOf(first));
			pstmt.setDate(3, Date.valueOf(last));
			
			rs = pstmt.executeQuery();
			
			Map<LocalDate, Integer> cntMap = new HashMap<>();
			while(rs.next()) {
				//가져온 날짜를 셋팅함.
				LocalDate key = rs.getDate("date").toLocalDate();
				Integer value = rs.getInt("cnt");
				cntMap.put(key, value);
			}
			
			for(DayController day : dayList) {
				day.setDayLabel(calendarDate);
				if(cntMap.containsKey(calendarDate)) {
					day.setCountLabel(cntMap.get(calendarDate));
				}else {
					day.setCountLabel(0); //값이 있으면 해당 값으로 아니면 0으로 셋팅
				}
				calendarDate = calendarDate.plusDays(1); //하루씩 증가
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 연결중 오류발생, 인터넷 연결을 확인하세요", AlertType.ERROR);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
		
		currentYM = ym;
	}
	
	public void logout() {
		MainApp.app.loadPane("login");
	}
	
	public void prevMonth() {
		loadMonthData(currentYM.minusMonths(1)); //한달 뺀 달력을 로드
		LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		setToday(firstDay);
	}
	
	public void nextMonth() {
		loadMonthData(currentYM.plusMonths(1)); //한달 뺀 달력을 로드
		LocalDate firstDay = LocalDate.of(currentYM.getYear(), currentYM.getMonthValue(), 1);
		setToday(firstDay);
	}
	
	public void setLoginInfo(UserVO user) {
		this.user = user;
		loginInfo.setText(user.getName() + "[" + user.getId() + "]");
		
		//로그인하지 않은상태에서 데이터를 불러오지 않도록 해당 코드를 initialize에서 이곳으로 옮겼다. 
		loadMonthData(YearMonth.now());
		setToday(LocalDate.now());
	}
	
	public void setClickData(LocalDate date) {
		setToday(date); //날짜 설정하고
		for(DayController dc : dayList) {
			dc.outFocus(); //모든 DayController에서 active를 제거한다.
		}
	}
	
	public UserVO getUser() {
		return user;
	}
}
