package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class MainController {
	@FXML
	private Button btnBack;
	@FXML
	private Button btnReload;
	@FXML
	private Button btnGo;
	@FXML
	private TextField txtUrl;
	@FXML
	private WebView webView;
	
	private WebEngine browser;
	
	private WebHistory history;
	
	@FXML
	private void initialize() {
		browser = webView.getEngine();
		browser.load("http://www.gmsgondr.net");
		history = browser.getHistory();
		btnBack.setDisable(true);
		history.currentIndexProperty().addListener((ob, o, n)->{
			if(n.intValue() == 0 && !btnBack.isDisable()) {
				btnBack.setDisable(true);
			}else if(n.intValue() != 0 && btnBack.isDisable()){
				btnBack.setDisable(false);
			}
		});
	}
	
	public void loadPage() {
		String url = txtUrl.getText();
		if(url.indexOf("http://") < 0 && url.indexOf("https://") < 0) {
			url = "http://" + url;
		}
		browser.load(url);
		String location = browser.getLocation();
		txtUrl.setText(location);
	}
	
	public void backPage() {
		int idx = history.currentIndexProperty().get();
		if(idx > 0) {
			history.go(-1);
		}
	}
	
	public void reloadPage() {
		browser.reload();
	}
	
	public void keypressHandle(KeyEvent e) {
		if( e.getCode() == KeyCode.ENTER ) {
			loadPage();
		}
	}
}
