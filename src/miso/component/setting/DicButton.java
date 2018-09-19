package miso.component.setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.scene.control.Tab;
import javafx.scene.web.WebView;
import miso.resource.IAPIKeys;
import miso.utility.LanguageDetector;
import miso.utility.Scope;

public class DicButton extends SettingButton {

	public DicButton() {
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;" + "-fx-border-radius:64.0px;"
				+ "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;" + "-fx-background-color: white;");
	}

	@Override
	public void actionOff() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #e2e2e2;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_dictionary.png')");
	}

	@Override
	public void actionOn() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_dictionary.png')");
	}

	@Override
	public void search(String keyword, WebView browser) {
		String target = null;
		String url;

		String str = Scope.getInstace().getPrefInfo().getDicAPI();

		switch (str) {

		case "Naver":
			naverAPIaction(keyword, browser);
			break;
		case "Daum":
			daumAPIaction(keyword, browser);
			break;
		default:
			break;
		}

	}

	@Override
	public void makeHtml(String keyword) {
		// TODO Auto-generated method stub

	}

	public boolean containsHanScript(String s) {
		return s.codePoints()
				.anyMatch(codepoint -> Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN);
	}

	public void naverAPIaction(String keyword, WebView browser) {
		String target = null;
		String url;
		LanguageDetector detector = new LanguageDetector();
		String keywordLanguage = detector.detectionLanguage(keyword);
		try {
			String searchWord = URLEncoder.encode(keyword, "UTF-8");

			switch (keywordLanguage) {
			case "ko":
				url = "https://ko.dict.naver.com/search.nhn?dic_where=krdic&query=" + searchWord;
				break;
			case "ja":
				url = "https://ja.dict.naver.com/search.nhn?query=" + searchWord;
				break;
			case "en":
				url = "https://endic.naver.com/search.nhn?sLn=kr&query=" + searchWord;
				break;
			case "zh-CN":
				url = "https://zh.dict.naver.com/#/search?query=" + searchWord;
				break;
			case "vi":
				url = "https://endic.naver.com/search.nhn?sLn=kr&query=" + searchWord;
				break;
			default:
				url = "https://hanja.dict.naver.com/search?query=" + searchWord;
				break;
			}

			browser.getEngine().load(url);
			resultTab = new Tab("어학사전", browser);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void daumAPIaction(String keyword, WebView browser) {
		String target = null;
		String url;
		try {
			String searchWord = URLEncoder.encode(keyword, "UTF-8");

			url = "http://alldic.daum.net/search.do?q=" + searchWord;
			browser.getEngine().load(url);
			resultTab = new Tab("어학사전", browser);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String detectionLanguage(String keyword) {
		String text = null;
		String dLanguage = null;
		StringBuffer response = new StringBuffer();
		try {
			String query = URLEncoder.encode(keyword, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/papago/detectLangs";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", IAPIKeys.NaverClientId);
			con.setRequestProperty("X-Naver-Client-Secret", IAPIKeys.NaverClientSecret);
			// post request
			String postParams = "query=" + query;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
			dLanguage = (String) jsonObject.get("langCode");
			System.out.println(dLanguage);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dLanguage;
	}

}