package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.MainApp;
import util.JDBCUtil;
import util.Util;

public class RegisterController extends MasterController{
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField passConfirm;
	@FXML
	private TextArea txtInfo;
	
	@FXML
	private AnchorPane registerPage;
	
	public void register() {
		String id = txtId.getText().trim();
		String name = txtName.getText().trim();
		String strPass = pass.getText().trim();
		String confirm = passConfirm.getText().trim();
		String info = txtInfo.getText().trim();
		
		if(id.isEmpty() || name.isEmpty() || strPass.isEmpty() || info.isEmpty()) {
			Util.showAlert("ºñ¾îÀÖÀ½", "ÇÊ¼ö ÀÔ·ÂÃ¢ÀÌ ºñ¾îÀÖ½À´Ï´Ù.", AlertType.INFORMATION);
			return;
		}
		
		if(!strPass.equals(confirm)) {
			Util.showAlert("ºÒÀÏÄ¡", "ºñ¹Ğ¹øÈ£¿Í ºñ¹Ğ¹øÈ£ È®ÀÎÀÌ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.", AlertType.INFORMATION);
			return;
		}
		
		if (!id.matches("[a-zA-Z0-9]{4,8}")) {
			Util.showAlert("ºÒÀÏÄ¡", "¾ÆÀÌµğ´Â ¿µ¹® ¹× ¼ıÀÚ·Î¸¸ ÀÌ·ç¾îÁö¸ç 4±ÛÀÚ ÀÌ»ó 8±ÛÀÚ ÀÌÇÏ·Î ¸¸µé¾î¾ß ÇÕ´Ï´Ù.", AlertType.INFORMATION);
			return;
		}
		
		if(!strPass.matches("[a-zA-Z0-9]{8,}")) {
			Util.showAlert("ºÒÀÏÄ¡", "ºñ¹Ğ¹øÈ£´Â ÃÖ¼Ò 8±ÛÀÚÀÌ»óÀÇ ¿µ¹® ¹× ¼ıÀÚÁ¶ÇÕÀ¸·Î ÀÌ·ç¾îÁ®¾ß ÇÕ´Ï´Ù.", AlertType.INFORMATION);
			return;
		}
		
		if(!name.matches("[°¡-ÆR]{2,}")) {
			Util.showAlert("ºÒÀÏÄ¡", "ÀÌ¸§Àº ÇÑ±Û·Î ÃÖ¼Ò 2±ÛÀÚ ÀÌ»óÀÌ¾î¾ß ÇÕ´Ï´Ù.", AlertType.INFORMATION);
			return;
		}
		
		
		Connection con = JDBCUtil.getConnection(); //µ¥ÀÌÅÍº£ÀÌ½º ¿¬°á °¡Á®¿À°í
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlExist = "SELECT * FROM diary_users WHERE id = ?"; //ÇØ´ç À¯Àú°¡ Á¸ÀçÇÏ´ÂÁö È®ÀÎÇÏ±â À§ÇÑ sql
		String sqlInsert = "INSERT INTO diary_users(`id`, `name`, `pass`, `info`) VALUES (?, ?, PASSWORD(?), ?)";
		
		try {
			pstmt = con.prepareStatement(sqlExist); //Á¸ÀçÇÏ´ÂÁö È®ÀÎÇÏ´Â sqlÀ» ÁØºñÇÏ°í
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); 
			if(rs.next()) { //ÀÌ¹Ì ´Ù¸¥ À¯Àú°¡ Á¸ÀçÇÔ.
				Util.showAlert("È¸¿ø Áßº¹", "ÀÌ¹Ì ÇØ´ç idÀÇ À¯Àú°¡ Á¸ÀçÇÕ´Ï´Ù. ´Ù¸¥ ¾ÆÀÌµğ¸¦ »ç¿ëÇÏ¼¼¿ä.", AlertType.INFORMATION);
				return;
			}
			
			JDBCUtil.close(pstmt);
			
			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, strPass);
			pstmt.setString(4, info);
			
			if( pstmt.executeUpdate() != 1) {
				Util.showAlert("¿¡·¯", "µ¥ÀÌÅÍº£ÀÌ½º¿¡ È¸¿øÁ¤º¸°¡ ¿Ã¹Ù¸£°Ô ÀÔ·ÂµÇÁö ¾Ê¾Ò½À´Ï´Ù. ÀÔ·Â°ªÀ» È®ÀÎÇÏ¼¼¿ä.", AlertType.ERROR);
				return;
			}
			
			Util.showAlert("¼º°ø", "È¸¿ø°¡ÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù. ·Î±×ÀÎÇØÁÖ¼¼¿ä.", AlertType.INFORMATION);
			MainApp.app.slideOut(getRoot()); //·çÆ®¸¦ Àâ¾Æ¼­ ½½¶óÀÌµå ¾Æ¿ô ½ÃÅ´.
			
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("¿¡·¯", "µ¥ÀÌÅÍº£ÀÌ½º ¿¬°áÁß ¿À·ù°¡ ¹ß»ıÇß½À´Ï´Ù. ÀÎÅÍ³İ ¿¬°áÀ» È®ÀÎÇÏ¼¼¿ä.", AlertType.ERROR);
			return;
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void cancel() {
		MainApp.app.slideOut(getRoot()); //·çÆ®¸¦ Àâ¾Æ¼­ ½½¶óÀÌµå ¾Æ¿ô ½ÃÅ´.
	}
}
