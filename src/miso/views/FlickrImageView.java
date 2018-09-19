package miso.views;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.pdfbox.jbig2.Bitmap;
import org.apache.pdfbox.jbig2.image.Bitmaps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jfoenix.controls.JFXTabPane;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FlickrImageView extends Tab {

   @FXML
   private AnchorPane imagePane;

   private String SEARCH_URL = "https://secure.flickr.com/services/rest/?method=flickr.photos.search";
   private String API_KEY = "&api_key=8be03693984223759e127f683af09b72";
   private int number = 30;
   private String PER_PAGE = "&per_page=" + number;
   private String SORT = "&sort=interestingness-desc";
   private String FORMAT = "&format=json";
   private String CONTECT_TYPE = "&content_type=1";
   private String SEARCH_TEXT = "&text=";
   private String URL = SEARCH_URL + API_KEY + PER_PAGE + SORT + FORMAT + CONTECT_TYPE + SEARCH_TEXT;

   private String fxml = "/miso/fxml/flickImage.fxml";
   private JFXTabPane root;
   private ArrayList<ImageView> imageArray = new ArrayList<ImageView>();
   private Vector<Image> imageVector = new Vector<Image>();
   private Vector<HashMap<String, String>> photoinfoList = new Vector<HashMap<String, String>>();
   private Vector<String> urlVector = new Vector<String>();

   private String keyword;

   public FlickrImageView() {

   }

   public void load() {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
         root = loader.load();

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public JFXTabPane getRoot() {
      return root;
   }

   public void setRoot(JFXTabPane root) {
      this.root = root;
   }

   public void settingImageView() throws IOException {
      AnchorPane a = (AnchorPane) root.getTabs().get(0).getContent();
      ScrollPane sp = (ScrollPane) a.getChildren().get(0);
      URL url = new URL(URL + keyword);
      System.out.println(url);

      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
      String result = null;
      imagePane = (AnchorPane) sp.getContent();
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setDoInput(true);
      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setUseCaches(false);
      httpURLConnection.connect();

      int responseStatusCode = httpURLConnection.getResponseCode();

      InputStream inputStream;
      if (responseStatusCode == HttpURLConnection.HTTP_OK) {

         inputStream = httpURLConnection.getInputStream();
         System.out.println("connection ok");
      } else {
         inputStream = httpURLConnection.getErrorStream();
         System.out.println("connection error");
      }
      System.out.println("3");
      try {

         InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
         StringBuffer sb = new StringBuffer(); // async
         String line;
         int cp;
         while ((cp = bufferedReader.read()) != -1) {
            sb.append((char) cp);

         }

         String str = URLEncoder.encode(sb.toString(), "UTF-8");

         bufferedReader.close();
         httpURLConnection.disconnect();
         result = sb.toString().trim();

      } catch (Exception e) {
         result = e.toString();
      }

      jsonParser(result);

      for (int i = 0; i < photoinfoList.size(); i++) {
         String id = photoinfoList.get(i).get("id");
         String secret = photoinfoList.get(i).get("secret");
         String server = photoinfoList.get(i).get("server");
         String farm = photoinfoList.get(i).get("farm");
         String title = photoinfoList.get(i).get("title");

         String largePhotoURL = "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret
               + "_t.jpg";
         urlVector.add(largePhotoURL);

         URL imageUrl = null;
         try {
            imageUrl = new URL(largePhotoURL);
         } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         Image img = null;
         BufferedImage image = ImageIO.read(imageUrl);
         img = SwingFXUtils.toFXImage(image, null);
         imageVector.add(img);

      }

      // Image image =
      // Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(url);

      ObservableList<Node> list = imagePane.getChildren();

      int y = 10;

      for (int i = 0; i < imageVector.size() / 5; i++) {
         if (imageVector.size() % 5 == 0) {
            for (int j = 0; j < imageVector.size() % 5; i++) {
               ImageView vv = new ImageView();
               vv.setImage(imageVector.get((i * 5) + j));
               list.add(vv);
               vv.setLayoutX(10 + j * 130);
               vv.setLayoutY(y);
            }
         }
         for (int j = 0; j < 5; j++) {
            ImageView vv = new ImageView();
            vv.setImage(imageVector.get((i * 5) + j));
            list.add(vv);
            vv.setLayoutX(10 + j * 130);
            vv.setLayoutY(y);

         }
         y += 130;
         System.out.println(i);
      }

   }

   public void setKeyword(String keyword) {
      // TODO Auto-generated method stub
      this.keyword = keyword;
   }

   public void jsonParser(String jsonString) {
      if (jsonString == null)
         return;

      jsonString = jsonString.replace("jsonFlickrApi(", "");
      jsonString = jsonString.replace(")", "");

      System.out.println("jsonString =  " + jsonString);

      try {
         JSONParser parser = new JSONParser();

         JSONObject jsonObject = new JSONObject();

         jsonObject = (JSONObject) parser.parse(jsonString);

         JSONObject photos = (JSONObject) jsonObject.get("photos");

         JSONArray photo = (JSONArray) photos.get("photo");


         photoinfoList.clear();

         for (int i = 0; i < photo.size(); i++) {
            JSONObject photoInfo = (JSONObject) photo.get(i);
            String id = (String) photoInfo.get("id");
            String secret = (String) photoInfo.get("secret");
            String server = (String) photoInfo.get("server");
            String farm = (String) photoInfo.get("farm").toString();
            String title = (String) photoInfo.get("title");

            HashMap<String, String> photoinfoMap = new HashMap<String, String>();
            photoinfoMap.put("id", id);
            photoinfoMap.put("secret", secret);
            photoinfoMap.put("server", server);
            photoinfoMap.put("farm", farm);
            photoinfoMap.put("title", title);

            photoinfoList.add(photoinfoMap);

         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}