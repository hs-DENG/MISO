package miso.component.setting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.effect.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import miso.actions.SearchModule;
import miso.utility.Scope;

abstract public class SettingButton extends JFXButton {
	protected boolean clicked = false;

	protected Scope scope;
	protected Tab resultTab;
	protected WebView myBrowser;
//	protected final WebEngine webEngine = browser.getEngine();*/
	protected ColorAdjust colorAdjust = new ColorAdjust();
	protected String api;

	public SettingButton() {

		addEventHandler(ActionEvent.ACTION, e -> {
			action(e);
		});
	}

	protected void action(ActionEvent e) {
		if (!clicked) {
			clicked = true;
			System.out.println(e.getSource().getClass());
			setFocused(false);
			Scope.getInstace().getSearchTextField().requestFocus();
			SearchModule.getInstance().addActionVector(this);
			actionOn();
		} else {
			clicked = false;
			setEffect(null);
			setFocused(false);
			Scope.getInstace().getSearchTextField().requestFocus();
			SearchModule.getInstance().removeActionVector(this);
			actionOff();
		}
	}

	abstract public void actionOff();

	abstract public void actionOn();

	abstract public void search(String keyword, WebView browser);

	public Tab getTab() {
		return resultTab;
	}

	// UTF-8 방식의 검색텍스트를 받아 검색 결과 주소 생성
	abstract public void makeHtml(String keyword);

	// get/set

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public String getAPI() {
		return api;
	}

	public void setAPI(String api) {
		this.api = api;
	}

	public WebView getMyBrowser() {
		return myBrowser;
	}

	public void setMyBrowser(WebView myBrowser) {
		this.myBrowser = myBrowser;
	}
}
