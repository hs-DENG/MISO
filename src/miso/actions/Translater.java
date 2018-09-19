package miso.actions;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.sound.sampled.AudioFormat.Encoding;

import miso.resource.IAPIKeys;
import miso.utility.LanguageDetector;
import miso.utility.Scope;

public class Translater {
	private String text;
	private String temp;
	public String clientId = IAPIKeys.NaverClientId;// 애플리케이션 클라이언트 아이디값";
	public String clientSecret = IAPIKeys.NaverClientSecret;// 애플리케이션 클라이언트 시크릿값";

	public Translater() {

	}

	public String translate(String word, String from, String to) {

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

		LanguageDetector detector = new LanguageDetector();
		String keywordLanguage = detector.detectionLanguage(word);

		if (keywordLanguage.equals(t)) {
			System.out.println(t+","+ f + " 64 line");
			text = translateFirst(word, t, f);
		} else {
			Scope scope = Scope.getInstace();
			scope.setTrsFrom(keywordLanguage);
			System.out.println(keywordLanguage+","+ t + " 69 line");
			text = translateFirst(word, keywordLanguage, t);
		}
		return text;
	}

	public String translateFirst(String word, String f, String t) {
		System.out.println(word);
		try {
			word = URLEncoder.encode(word, "UTF-8");

			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			// String url = "https://kapi.kakao.com/v1/translation/translate";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// post request
			String postParams = "source=" + f + "&target=" + t + "&text=" + word;

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
				if (inputLine.contains("translatedText")) {
					temp = lastTranslatedText(inputLine);
				}
			}
			br.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return temp;
	}

	public String lastTranslatedText(String text) {
		String resultText = "translatedText";
		int startIndex = text.indexOf(resultText) + resultText.length() + 3;
		int endIndex = startIndex + text.substring(startIndex).indexOf('"');

		return text.substring(startIndex, endIndex);
	}

}