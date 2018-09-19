package miso.views;

import java.io.IOException;
import java.util.Vector;

import com.jfoenix.controls.JFXRadioButton;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import miso.resource.InputVirtualKeys;
import miso.utility.PrefInfo;
import miso.utility.Scope;

public class PrefView extends View {

	private FXMLLoader loader;
	private BorderPane root;
	private String fxml = "/miso/fxml/prefView.fxml";
	private Scope scope = Scope.getInstace();
	
	public PrefView() {
	}

	@Override
	public void load() {
		try {
			primaryStage = new Stage();

			loader = new FXMLLoader(getClass().getResource(fxml));
			root = loader.load();
			
			scene = new Scene(root, 600, 440);
			Scope.getInstace().setPrefStage(primaryStage);

			primaryStage.setTitle("Preferences");
			primaryStage.getIcons().add(new Image("/miso/icon/misoTray.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e ->{
				scope.setPrefViewFlag(false);
			});
			scope.setPrefViewFlag(true);
			setResize();

			DicSettingView dicSettingView = new DicSettingView();
			EncSettingView encSettingView = new EncSettingView();
			HotKeySettingView hotKeySettingView = new HotKeySettingView();
			ImgSettingView imgSettingView = new ImgSettingView();
			MapSettingView mapSettingView = new MapSettingView();
			TrsSettingView trsSettingView = new TrsSettingView();
			VideoSettingView videoSettingView = new VideoSettingView();
			OcrSettingView ocrSettingView = new OcrSettingView();

			// pref load
			dicSettingView.load();
			encSettingView.load();
			hotKeySettingView.load();
			imgSettingView.load();
			mapSettingView.load();
			trsSettingView.load();
			videoSettingView.load();
			ocrSettingView.load();

			// scope connect
			Scope.getInstace().setDicBorder(dicSettingView.getBorder());
			Scope.getInstace().setEncBorder(encSettingView.getBorder());
			Scope.getInstace().setHotkeyBorder(hotKeySettingView.getBorder());
			Scope.getInstace().setImgBorder(imgSettingView.getBorder());
			Scope.getInstace().setMapBorder(mapSettingView.getBorder());
			Scope.getInstace().setTrsBorder(trsSettingView.getBorder());
			Scope.getInstace().setVideoBorder(videoSettingView.getBorder());
			Scope.getInstace().setOcrBorder(ocrSettingView.getBorder());

			Scope.getInstace().getPrefBorder().add(dicSettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(encSettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(hotKeySettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(imgSettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(mapSettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(trsSettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(videoSettingView.getBorder());
			Scope.getInstace().getPrefBorder().add(ocrSettingView.getBorder());

			// selected
			setSelected(dicSettingView);
			setSelected(encSettingView);
			setSelected(hotKeySettingView);
			setSelected(imgSettingView);
			setSelected(mapSettingView);
			setSelected(trsSettingView);
			setSelected(videoSettingView);
			setSelected(ocrSettingView);
			root.setRight(dicSettingView.getBorder());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setSelected(PrefBorder settingView) {
		PrefInfo prefInfo = Scope.getInstace().getPrefInfo();
		switch (settingView.getName()) {
		case "dic":
			AnchorPane dicAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane dicPane = (Pane) dicAncPane.getChildren().get(0);
			ObservableList<Node> dicNodeList = dicPane.getChildren();
			for (int i = 0; i < dicNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) dicNodeList.get(i);
				if (selected.getText().equals(prefInfo.getDicAPI())) {
					selected.setSelected(true);
				}
			}
			break;
		case "enc":
			AnchorPane encAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane encPane = (Pane) encAncPane.getChildren().get(0);
			ObservableList<Node> encNodeList = encPane.getChildren();
			for (int i = 0; i < encNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) encNodeList.get(i);
				if (selected.getText().equals(prefInfo.getEncAPI())) {
					selected.setSelected(true);
				}
			}
			break;
		case "hotkey":
			AnchorPane hotAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane hotPane = (Pane) hotAncPane.getChildren().get(2);
			Pane ocrPane = (Pane) hotAncPane.getChildren().get(3);
			ObservableList<Node> hotNodeList = hotPane.getChildren();
			ObservableList<Node> ocrNodeList = ocrPane.getChildren();
			for (int i = 0; i < hotNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) hotNodeList.get(i);
				switch (selected.getText()) {
				case "Wheel Mouse":
					if (prefInfo.getHotKey() == InputVirtualKeys.Mouse_Middle)
						selected.setSelected(true);
					break;
				case "Ctrl":
					if (prefInfo.getHotKey() == InputVirtualKeys.KeyBoard_Ctrl)
						selected.setSelected(true);
					break;
				case "Shift":
					if (prefInfo.getHotKey() == InputVirtualKeys.KeyBoard_Shift)
						selected.setSelected(true);
					break;
				case "Alt":
					if (prefInfo.getHotKey() == InputVirtualKeys.KeyBoard_Alt)
						selected.setSelected(true);
					break;
				case "F1":
					if (prefInfo.getHotKey() == InputVirtualKeys.KeyBoard_F1)
						selected.setSelected(true);
					break;
				case "F2":
					if (prefInfo.getHotKey() == InputVirtualKeys.KeyBoard_F2)
						selected.setSelected(true);
					break;
				case "F3":
					if (prefInfo.getHotKey() == InputVirtualKeys.KeyBoard_F3)
						selected.setSelected(true);
					break;
				}
			}
			for (int i = 0; i < ocrNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) ocrNodeList.get(i);
				switch (selected.getText()) {
				case "Ctrl":
					if (prefInfo.getOcrKey() == InputVirtualKeys.KeyBoard_Ctrl)
						selected.setSelected(true);
					break;
				case "Shift":
					if (prefInfo.getOcrKey() == InputVirtualKeys.KeyBoard_Shift)
						selected.setSelected(true);
					break;
				case "Alt":
					if (prefInfo.getOcrKey() == InputVirtualKeys.KeyBoard_Alt)
						selected.setSelected(true);
					break;
				case "F1":
					if (prefInfo.getOcrKey() == InputVirtualKeys.KeyBoard_F1)
						selected.setSelected(true);
					break;
				case "F2":
					if (prefInfo.getOcrKey() == InputVirtualKeys.KeyBoard_F2)
						selected.setSelected(true);
					break;
				case "F3":
					if (prefInfo.getOcrKey() == InputVirtualKeys.KeyBoard_F3)
						selected.setSelected(true);
					break;
				}
			}
			break;
		case "img":
			AnchorPane imgAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane imgPane = (Pane) imgAncPane.getChildren().get(0);
			ObservableList<Node> imgNodeList = imgPane.getChildren();
			for (int i = 0; i < imgNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) imgNodeList.get(i);

				if (selected.getText().equals(prefInfo.getImgAPI())) {

					selected.setSelected(true);
				}
			}
			break;
		case "map":
			AnchorPane mapAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane mapPane = (Pane) mapAncPane.getChildren().get(0);
			ObservableList<Node> mapNodeList = mapPane.getChildren();
			for (int i = 0; i < mapNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) mapNodeList.get(i);

				if (selected.getText().equals(prefInfo.getMapAPI())) {

					selected.setSelected(true);
				}
			}
			break;
		case "trs":
			AnchorPane tlsAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane tlsPane = (Pane) tlsAncPane.getChildren().get(0);
			ObservableList<Node> tlsNodeList = tlsPane.getChildren();
			for (int i = 0; i < tlsNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) tlsNodeList.get(i);
				if (selected.getText().equals(prefInfo.getTrsAPI())) {
					selected.setSelected(true);
				}
			}
			break;
		case "video":
			AnchorPane videoAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane videoPane = (Pane) videoAncPane.getChildren().get(0);
			ObservableList<Node> videoNodeList = videoPane.getChildren();
			for (int i = 0; i < videoNodeList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) videoNodeList.get(i);
				if (selected.getText().equals(prefInfo.getVideoAPI())) {
					selected.setSelected(true);
				}
			}
			break;
		case "ocrLanguage":
			AnchorPane ocrLangunageAncPane = (AnchorPane) settingView.getBorder().getCenter();
			Pane ocrlanguagePane = (Pane) ocrLangunageAncPane.getChildren().get(0);
			ObservableList<Node> ocrlanguageList = ocrlanguagePane.getChildren();
			for (int i = 0; i < ocrlanguageList.size(); i++) {
				JFXRadioButton selected = (JFXRadioButton) ocrlanguageList.get(i);
				if (selected.getText().equals(prefInfo.getOcrLanguage())) {
					selected.setSelected(true);
				}
			}
			break;

		}
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

}