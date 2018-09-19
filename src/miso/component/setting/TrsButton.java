package miso.component.setting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jfoenix.controls.JFXTabPane;

import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebView;
import miso.actions.Translater;
import miso.utility.Scope;
import miso.views.TrsTabView;

public class TrsButton extends SettingButton {
	private String fxml = "/miso/fxml/tlsTab.fxml";
	private int fromIndex;
	private int toIndex;

	@Override
	public void actionOff() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #e2e2e2;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_translate.png')");
	}

	@Override
	public void actionOn() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_translate.png')");
	}

	@Override
	public void makeHtml(String keyword) {
		// TODO Auto-generated method stub
	}

	@Override
	public void search(String keyword, WebView browser) {

		String str = Scope.getInstace().getPrefInfo().getTrsAPI();

		switch (str) {

		case "Naver":
			naverAPIaction(keyword, browser);
			break;
		case "Google":
			googleAPIaction(keyword, browser);
			break;
		default:
			break;
		}

	}

	public void googleAPIaction(String keyword, WebView browser) {
		String text = null;
		String from = scope.getTrsFrom();
		String to = scope.getTrsTo();
		try {
			text = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(from + " , " + to);
		String f = from;
		String t = to;
		switch (from) {
		case "Korean":
			f = "ko";
			break;
		case "Japanese":
			f = "ja";
			break;
		case "Chinese":
			f = "zh-CN";
			break;
		case "English":
			f = "en";
			break;
		}

		switch (to) {
		case "Korean":
			t = "ko";
			break;
		case "Japanese":
			t = "ja";
			break;
		case "Chinese":
			t = "zh-CN";
			break;
		case "English":
			t = "en";
			break;
		}
		

	String url = "https://translate.google.co.kr/?hl=ko#" + f + "/" + t + "/" + text;
		//String url = "https://translate.google.co.kr/?hl=ko#en/ko/lion";
		browser.getEngine().load(url);
		resultTab = new Tab("번역", browser);
	}

	public void naverAPIaction(String keyword, WebView browser) {
		Translater translater = new Translater();
		String encodingTtext = null;
		String from = scope.getTrsFrom();
		String to = scope.getTrsTo();
		String translatedText = translater.translate(keyword, from, to); // resultText (영어를 한글로 번역완료된 언어)
		System.out.println("translateText is " +translatedText );
		TrsTabView view = new TrsTabView();
		view.load();
        Scope.getInstace().getSearchKey().setWrapText(true);
        Scope.getInstace().getTlsResult().setWrappingWidth(550);
		Scope.getInstace().getSearchKey().setText(keyword);
		Scope.getInstace().getTlsResult().setText(translatedText);
		AnchorPane pane = (AnchorPane) view.getRoot().getTabs().get(0).getContent();
		resultTab = new Tab("Translate", pane);
		System.out.println(resultTab + " tlsButton");

	}

}