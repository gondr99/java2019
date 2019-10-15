package views;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import domain.TodoVO;
import domain.UserVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.MainApp;
import util.JDBCUtil;
import util.Util;

public class TodoController extends MasterController{
	@FXML
	private TextField txtTitle;
	@FXML
	private TextArea txtContent;
	@FXML
	private ListView<TodoVO> todoList;
	
	@FXML
	private TextField todoTitle;
	@FXML
	private TextArea todoContent;
	
	private LocalDate date;
	private ObservableList<TodoVO> list;

	//초기화때 연결되는 관찰리스트를 만들어준다.
	@FXML
	private void initialize() {
		list = FXCollections.observableArrayList();
		todoList.setItems(list);
	}
	
	public void init(LocalDate date) {
		txtTitle.setText("");
		txtContent.setText("");
		todoTitle.setText("");
		todoContent.setText("");
		
		this.date = date;
		UserVO user = MainApp.app.getLoginUser();
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		list.clear(); //기존에 있던 일정들 비워주고
		//해당 유저의 해당 날짜의 일정을 모두 가져온다.
		String sql = "SELECT * FROM diary_todos WHERE date = ? AND owner = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(this.date));
			pstmt.setString(2, user.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TodoVO vo = new TodoVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDate(rs.getDate("date").toLocalDate());
				vo.setOwner(rs.getString("owner"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 접속중 오류 발생, 인터넷 연결을 확인하세요." , AlertType.ERROR);
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	//일정신규등록
	public void register() {
		String title = txtTitle.getText();
		String content = txtContent.getText();
		UserVO user = MainApp.app.getLoginUser();
		
		if(title.isEmpty() || content.isEmpty()) {
			Util.showAlert("필수항목 비어있음", "제목이나 내용은 모두 비어있을 수 없습니다", AlertType.INFORMATION);
			return;
		}
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO diary_todos(title, content, date, owner) VALUES(?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setDate(3, Date.valueOf(this.date));
			pstmt.setString(4, user.getId());
			int result = pstmt.executeUpdate();
			
			if(result != 1) {
				Util.showAlert("에러", "데이터베이스에 정상적으로 입력되지 않았습니다. 재시도해주세요.", AlertType.ERROR);
				return;
			}
			Util.showAlert("성공", "데이터베이스에 정상적으로 입력었습니다.", AlertType.INFORMATION);
			
			MainApp.app.slideOut(this.getRoot());
			
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 연결중 오류 발생. 인터넷 상태를 확인하세요.", AlertType.ERROR);
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	//일정 수정
	public void update() {
		String title = todoTitle.getText();
		String content = todoContent.getText();
		UserVO user = MainApp.app.getLoginUser();
		
		if(title.isEmpty() || content.isEmpty()) {
			Util.showAlert("필수항목 비어있음", "제목이나 내용은 모두 비어있을 수 없습니다", AlertType.INFORMATION);
			return;
		}

		int idx = todoList.getSelectionModel().getSelectedIndex();
		if(idx < 0) {
			return;
		}
		
		TodoVO todo = list.get(idx);
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE diary_todos SET title = ?, content = ? WHERE id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, todo.getId());
			int result = pstmt.executeUpdate();
			
			if(result != 1) {
				Util.showAlert("에러", "데이터베이스 처리중 오류 발생. 입력문자를 확인하세요.", AlertType.ERROR);
				return;
			}
			
			Util.showAlert("성공", "데이터베이스에 정상적으로 수정되었습니다.", AlertType.INFORMATION);
			MainApp.app.slideOut(this.getRoot());
			
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 연결중 오류 발생. 인터넷 상태를 확인하세요.", AlertType.ERROR);
		}finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	//일정창 닫기
	public void close() {
		MainApp.app.slideOut(this.getRoot());		
	}
	
	//리스트 뷰 클릭시 데이터 가져오기
	public void loadTodo() {
		int idx = todoList.getSelectionModel().getSelectedIndex();
		if(idx < 0) {
			return;
		}
		
		TodoVO vo = list.get(idx); //클릭한 녀석의 데이터를 가져와서
		todoContent.setText(vo.getContent());
		todoTitle.setText(vo.getTitle());
	}
	
}
