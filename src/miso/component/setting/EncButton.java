package miso.component.setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;
import miso.resource.IAPIKeys;
import miso.utility.Scope;

public class EncButton extends SettingButton {

	@Override
	public void actionOff() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #e2e2e2;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/brown_encyclopedia.png')");
	}

	@Override
	public void actionOn() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/brown_encyclopedia.png')");
	}

	@Override
	public void makeHtml(String keyword) {
		try {
			String text = URLEncoder.encode(keyword, "utf-8");
			String apiURL = "https://openapi.naver.com/v1/search/encyc.xml?query=" + text;
			// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
			// // xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", IAPIKeys.NaverClientId);
			con.setRequestProperty("X-Naver-Client-Secret", IAPIKeys.NaverClientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("src/miso/xml/encResult.xml"), "utf-8"));
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
				bw.write(inputLine);
				bw.newLine();
			}
			bw.close();
			br.close();

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Source xslDoc = new StreamSource("src/miso/xsl/encXSL.xsl");
			Source xmlDoc = new StreamSource("./xml/encResult.xml");

			String outputFileName = "./html/encResult.html";
			OutputStream htmlFile = new FileOutputStream(outputFileName);

			Transformer transformer = tFactory.newTransformer(xslDoc);
			transformer.transform(xmlDoc, new StreamResult(htmlFile));

		} catch (Exception e) {
		}

	}

	@Override
	public void search(String keyword,WebView browser) {
		String str = Scope.getInstace().getPrefInfo().getEncAPI();

		switch (str) {

		case "Naver":
			naverAPIaction(keyword,browser);
			break;
		case "Wikipedia":
			wikiAPIaction(keyword,browser);
			break;
		default:
			break;
		}

	}

	public void naverAPIaction(String keyword,WebView browser) {
		try {
			String searchWord = URLEncoder.encode(keyword, "UTF-8");
			File htmlFile = new File("./html/encResult.html");

			browser.getEngine().load(htmlFile.toURI().toURL().toString());
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resultTab = new Tab("백과사전", browser);
	}

	public void wikiAPIaction(String keyword,WebView browser) {

		String searchWord = null;
		try {
			searchWord = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "https://ko.wikipedia.org/wiki/" + keyword;
		browser.getEngine().load(url);

		resultTab = new Tab("백과사전", browser);
	}

}