package miso.component.setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import miso.resource.IAPIKeys;
import miso.utility.Scope;
import miso.views.FlickrImageView;

public class ImgButton extends SettingButton {

	@Override
	public void actionOff() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #e2e2e2;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_picture.png')");
	}

	@Override
	public void actionOn() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_picture.png')");
	}

   @Override
   public void makeHtml(String keyword) {

   }

   @Override
   public void search(String keyword,WebView browser) {

      String str = Scope.getInstace().getPrefInfo().getImgAPI();

      switch (str) {

      case "Naver":
         naverAPIaction(keyword,browser);
         break;
      case "Flickr":
         flickrAPIaction(keyword,browser);
         break;
      default:
         break;
      }

   }

   public void flickrAPIaction(String keyword,WebView browser) {
      // TODO Auto-generated method stub

      FlickrImageView view = new FlickrImageView();
      System.out.println("flickrIageView load not yet");
      view.load();
      view.setKeyword(keyword);
      System.out.println("flickrIageView load success");
      AnchorPane a = (AnchorPane) view.getRoot().getTabs().get(0).getContent();
      try {
         view.settingImageView();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         System.out.println("exception");
         e.printStackTrace();
      }
      resultTab = new Tab("이미지", a);

   }

   public void naverAPIaction(String keyword,WebView browser) {
      try {
         String text = URLEncoder.encode(keyword, "UTF-8");
         String apiURL = "https://openapi.naver.com/v1/search/image.xml?query=" + text + "&display=100"; // json 결과
         // String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
         // // xml 결과
         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("X-Naver-Client-Id", IAPIKeys.NaverClientId);
         con.setRequestProperty("X-Naver-Client-Secret", IAPIKeys.NaverClientSecret);
         int responseCode = con.getResponseCode();
         BufferedReader br;
         BufferedWriter bw = new BufferedWriter(
               new OutputStreamWriter(new FileOutputStream("./xml/imgResult.xml"), "utf-8"));

         if (responseCode == 200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
         } else { // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));

         }
         String inputLine;
         StringBuffer response = new StringBuffer();
         while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
            bw.write(inputLine);
            bw.newLine();
         }

         bw.close();
         br.close();
         TransformerFactory tFactory = TransformerFactory.newInstance();

         Source xslDoc = new StreamSource("src/miso/xsl/imgXSL.xsl");
         Source xmlDoc = new StreamSource("./xml/imgResult.xml");

         String outputFileName = "./html/imgResult.html";
         OutputStream htmlFile = new FileOutputStream(outputFileName);

         Transformer transformer = tFactory.newTransformer(xslDoc);
         transformer.transform(xmlDoc, new StreamResult(htmlFile));

      } catch (Exception e) {
         System.out.println(e);
      }

      File htmlFile = new File("./html/imgResult.html");
      try {
         browser.getEngine().load(htmlFile.toURI().toURL().toString());
      } catch (MalformedURLException ex) {
         ex.printStackTrace();
      }
      resultTab = new Tab("이미지", browser);

   }

}