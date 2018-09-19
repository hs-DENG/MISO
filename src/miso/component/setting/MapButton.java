package miso.component.setting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.sound.midi.Synthesizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

import javafx.scene.control.Tab;
import javafx.scene.web.WebView;
import miso.utility.Scope;

public class MapButton extends SettingButton {

	private String changeWord = "";

	@Override
	public void actionOff() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;" + "-fx-border-radius:64.0px;"
				+ "-fx-border-width:4.0px;" + "-fx-border-color: #e2e2e2;" + "-fx-background-color: white;"
				+ "-fx-graphic: url('/miso/icon/color_map2.png')");
	}

	@Override
	public void actionOn() {
		// TODO Auto-generated method stub
		setStyle("-fx-padding: 5.0 10.0 5.0 10.0;" + "-fx-background-radius:64.0px;" + "-fx-border-radius:64.0px;"
				+ "-fx-border-width:4.0px;" + "-fx-border-color: #F361A6;" + "-fx-background-color: white;"
				+ "-fx-graphic: url('/miso/icon/color_map2.png')");
	}

	@Override
	public void makeHtml(String keyword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void search(String keyword, WebView browser) {
		String searchWord = null;

		String str = Scope.getInstace().getPrefInfo().getMapAPI();

		switch (str) {

		case "Google":
			googleAPIaction(keyword, browser);
			break;
		case "Daum":
			daumAPIaction(keyword, browser);
			break;
		default:
			break;
		}

	}

	public void daumAPIaction(String keyword, WebView browser) {

		String target = null;
		try {
			String searchWord = URLEncoder.encode(keyword, "UTF-8");
			String url = "http://map.daum.net/link/search/" + searchWord;
			browser.getEngine().load(url);
			resultTab = new Tab("지도", browser);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultTab = new Tab("지도", browser);

	}

	public void googleAPIaction(String keyword, WebView browser) {

		StringBuffer response = new StringBuffer();
		try {
			String searchWord = URLEncoder.encode(keyword, "utf-8");

			File file = new File("./html/googleMap.html");
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("./html/googleSMap.html"), "utf-8"));

			HashMap<String, Object> hashMap = new HashMap<String, Object>();

			HashMap<String, Object> resultHash = getLatLon(hashMap, searchWord);

			double lat = (double) resultHash.get("lat");
			double lon = (double) resultHash.get("lon");
			String line = "";

			int flag = 0;

			while ((line = br.readLine()) != null) {
				response.append(line);
				if (line.contains("uluru") && flag == 0) {
					changeWord = "var uluru = {lat: " + lat + "," + "lng:" + lon + "};";
					bw.write(changeWord);
					bw.newLine();
					flag = 1;
				} else if (line.contains("center")) {
					changeWord = "  center:new google.maps.LatLng(" + lat + ", " + lon + "),";
					bw.write(changeWord);
					bw.newLine();
				} else {
					bw.write(line);
					bw.newLine();
				}
			}
			bw.close();
			br.close();
			File htmlFile;
			if (lat == 0.0) {
				htmlFile = new File("./html/notFound.html");
			} else {
				htmlFile = new File("./html/googleSMap.html");
			}
			browser.getEngine().load(htmlFile.toURI().toURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultTab = new Tab("지도", browser);
	}

	public HashMap<String, Object> getLatLon(HashMap<String, Object> hashMap, String searchWord) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<>();

		String json;

		StringBuilder sb = new StringBuilder();

		double doubleLat = 0.0;

		double doubleLon = 0.0;

		try {
//          String addr = "http://maps.google.com/maps/api/geocode/json?address=";

			String addr = "https://apis.daum.net/local/geo/addr2coord?apikey=";
			String apiKey = "cd4e0a08d5f3d7549bcf28b75603275a";
			URL url = new URL(addr + apiKey + "&q=" + searchWord + "&output=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {

				sb.append(line);
			}

			rd.close();

			conn.disconnect();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		json = sb.toString();
		JSONObject object = (JSONObject) JSONValue.parse(json);
		object = (JSONObject) object.get("channel");
		JSONArray array = (JSONArray) object.get("item");

		for (Object o : array) {

			JSONObject object2 = (JSONObject) o;

			String lat = object2.get("lat").toString();

			String lng = object2.get("lng").toString();

			doubleLat = Double.parseDouble(lat);

			doubleLon = Double.parseDouble(lng);

		}

		resultMap.put("lat", doubleLat);
		resultMap.put("lon", doubleLon);
		return resultMap;

	}

}