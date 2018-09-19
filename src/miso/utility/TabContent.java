package miso.utility;

import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import miso.component.setting.SettingButton;

public class TabContent {
	private WebView browser;
	private Tab tab;

	public TabContent() {
		browser = new WebView();
		// TODO Auto-generated constructor stub
	}

	public void addTabListener() {

	}

	public WebView getBrowser() {
		return browser;
	}

	public void setBrowser(WebView browser) {
		this.browser = browser;
	}

	public Tab getTab() {
		return tab;
	}

	public void setTab(Tab tab) {
		this.tab = tab;
	}

	public void shutdownWeb() {
		browser.getEngine().load(null);
	}



}
