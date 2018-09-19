package miso.component.setting;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javafx.event.ActionEvent;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import miso.utility.Scope;

public class VideoButton extends SettingButton {

	@Override
	public void actionOff() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #e2e2e2;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_youtube.png')");
	}

	@Override
	public void actionOn() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;"
				+ "-fx-border-radius:64.0px;" + "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;"
				+ "-fx-background-color: white;" + "-fx-graphic: url('/miso/icon/color_youtube.png')");
	}

   @Override
   public void makeHtml(String keyword) {
      // TODO Auto-generated method stub
   }

   @Override
   public void search(String keyword,WebView browser) {

      String str = Scope.getInstace().getPrefInfo().getVideoAPI();

      switch (str) {

      case "Naver":
         naverAPIaction(keyword,browser);
         break;
      case "Daum":
         daumAPIaction(keyword,browser);
         break;
      case "Youtube":
         youtubeAPIaction(keyword,browser);
      default:
         break;
      }
   }

   private void youtubeAPIaction(String keyword,WebView browser) {
      try {
         String text = URLEncoder.encode(keyword, "UTF-8");
         WebEngine webEngine = browser.getEngine();
         String url = "https://www.youtube.com/results?search_query=" + text;

         webEngine.load(url);

      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      resultTab = new Tab("비디오", browser);
   }

   private void daumAPIaction(String keyword,WebView browser) {
      try {
         String text = URLEncoder.encode(keyword, "UTF-8");
         WebEngine webEngine = browser.getEngine();
         String url = "https://tv.kakao.com/search?q=" + text;

         webEngine.load(url);

      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      resultTab = new Tab("비디오", browser);

   }

   private void naverAPIaction(String keyword,WebView browser) {
      try {
         String text = URLEncoder.encode(keyword, "UTF-8");
         WebEngine webEngine = browser.getEngine();
         String url = "https://tv.naver.com/search/clip?query=" + text + "&sort=rel&isTag=false";

         webEngine.load(url);

      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      resultTab = new Tab("비디오", browser);

   }

}