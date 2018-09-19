package miso.component.pref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import miso.resource.InputVirtualKeys;
import miso.utility.PrefInfo;
import miso.utility.Scope;

public class ApplyandCloseButton extends JFXButton {

	private FileOutputStream out = null;
	private static final String prefInfo = "./preference/prefInfo.txt";
	private Scope scope = Scope.getInstace();
	
	
	public ApplyandCloseButton() {
		addEventHandler(ActionEvent.ACTION, e -> {
			applyandClose(e);
			scope.setPrefViewFlag(false);
		});
	}

	private void applyandClose(ActionEvent e) {
		System.out.println("apply and close!");
		PrefInfo pref = new PrefInfo();
		String[] line = new String[9];

		for (int in = 0; in < Scope.getInstace().getPrefBorder().size(); in++) {
			BorderPane setting = Scope.getInstace().getPrefBorder().get(in);
			AnchorPane center = (AnchorPane) setting.getCenter();
			switch (setting.getId()) {
			case "dicBorder":
				Pane dicPane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < dicPane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) dicPane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setDicAPI(selected.getText());
						line[0] = "dic:" + selected.getText() + "\n";
						break;
					}
				}
				break;
			case "encBorder":
				Pane encPane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < encPane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) encPane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setEncAPI(selected.getText());
						line[1] = "enc:" + selected.getText() + "\n";
						break;
					}
				}
				break;
			case "imgBorder":
				Pane imgPane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < imgPane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) imgPane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setImgAPI(selected.getText());
						line[2] = "img:" + selected.getText() + "\n";
						break;
					}
				}
				break;
			case "trsBorder":
				Pane tlsPane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < tlsPane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) tlsPane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setTrsAPI(selected.getText());
						line[3] = "trs:" + selected.getText() + "\n";
						break;
					}
				}
				break;
			case "mapBorder":
				Pane mapPane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < mapPane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) mapPane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setMapAPI(selected.getText());
						line[4] = "map:" + selected.getText() + "\n";
						break;
					}
				}
				break;
			case "videoBorder":
				Pane videoPane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < videoPane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) videoPane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setVideoAPI(selected.getText());
						line[5] = "video:" + selected.getText() + "\n";
						break;
					}
				}
				break;

			case "ocrBorder":
				Pane ocrLanguagePane = (Pane) center.getChildren().get(0);
				for (int i = 0; i < ocrLanguagePane.getChildren().size(); i++) {
					JFXRadioButton selected = (JFXRadioButton) ocrLanguagePane.getChildren().get(i);
					if (selected.isSelected()) {
						pref.setOcrLanguage(selected.getText());
						 Scope.getInstace().getCapture().settingLanguage(selected.getText());
						 line[8] = "ocrLanguage:" + selected.getText() + "\n";
						// 파일 입출력 해야함 위에코드
						break;
					}
				}
				break;

			case "hotBorder":
				Pane hotkey = (Pane) center.getChildren().get(2);
				Pane ocr = (Pane) center.getChildren().get(3);

				JFXRadioButton hotRadio = (JFXRadioButton) hotkey.getChildren().get(1);
				JFXRadioButton ocrRadio = (JFXRadioButton) ocr.getChildren().get(1);

				ToggleGroup hotGroup = hotRadio.getToggleGroup();
				ToggleGroup ocrGroup = ocrRadio.getToggleGroup();

				JFXRadioButton hotSelected = (JFXRadioButton) hotGroup.getSelectedToggle();
				JFXRadioButton ocrSelected = (JFXRadioButton) ocrGroup.getSelectedToggle();

				if (hotSelected.getText().equals(ocrSelected.getText())) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("WARNING");
					alert.setHeaderText(null);
					alert.setContentText("설정하신 Search Key 와 Ocr Key 가 겹칩니다.");
					alert.setResizable(false);
					alert.show();
					return;

				}

				switch (hotSelected.getText()) {
				case "Mouse Wheel":
					pref.setHotKey(-1);
					pref.setMouse(InputVirtualKeys.Mouse_Middle);
					line[7] = "hot:" + InputVirtualKeys.Mouse_Middle + "\n";
					break;
				case "Ctrl":
					pref.setMouse(-1);
					pref.setHotKey((int) InputVirtualKeys.KeyBoard_Ctrl);
					line[7] = "hot:" + InputVirtualKeys.KeyBoard_Ctrl + "\n";
					break;
				case "Shift":
					pref.setMouse(-1);
					pref.setHotKey((int) InputVirtualKeys.KeyBoard_Shift);
					line[7] = "hot:" + InputVirtualKeys.KeyBoard_Shift + "\n";
					break;
				case "Alt":
					pref.setMouse(-1);
					pref.setHotKey((int) InputVirtualKeys.KeyBoard_Alt);
					line[7] = "hot:" + InputVirtualKeys.KeyBoard_Alt + "\n";
					break;
				case "F1":
					pref.setMouse(-1);
					pref.setHotKey((int) InputVirtualKeys.KeyBoard_F1);
					line[7] = "hot:" + InputVirtualKeys.KeyBoard_F1 + "\n";
					break;
				case "F2":
					pref.setMouse(-1);
					pref.setHotKey((int) InputVirtualKeys.KeyBoard_F2);
					line[7] = "hot:" + InputVirtualKeys.KeyBoard_F2 + "\n";
					break;
				case "F3":
					pref.setMouse(-1);
					pref.setHotKey((int) InputVirtualKeys.KeyBoard_F3);
					line[7] = "hot:" + InputVirtualKeys.KeyBoard_F3 + "\n";
					break;
				}

				switch (ocrSelected.getText()) {
				case "Ctrl":
					pref.setOcrKey((int) InputVirtualKeys.KeyBoard_Ctrl);
					line[6] = "ocr:" + InputVirtualKeys.KeyBoard_Ctrl + "\n";
					break;
				case "Shift":
					pref.setOcrKey((int) InputVirtualKeys.KeyBoard_Shift);
					line[6] = "ocr:" + InputVirtualKeys.KeyBoard_Shift + "\n";
					break;
				case "Alt":
					pref.setOcrKey((int) InputVirtualKeys.KeyBoard_Alt);
					line[6] = "ocr:" + InputVirtualKeys.KeyBoard_Alt + "\n";
					break;
				case "F1":
					pref.setOcrKey((int) InputVirtualKeys.KeyBoard_F1);
					line[6] = "ocr:" + InputVirtualKeys.KeyBoard_F1 + "\n";
					break;
				case "F2":
					pref.setOcrKey((int) InputVirtualKeys.KeyBoard_F2);
					line[6] = "ocr:" + InputVirtualKeys.KeyBoard_F2 + "\n";
					break;
				case "F3":
					pref.setOcrKey((int) InputVirtualKeys.KeyBoard_F3);
					line[6] = "ocr:" + InputVirtualKeys.KeyBoard_F3 + "\n";
					break;
				}
			}
		}
		String doc = "";
		for (int i = 0; i < 9; i++) {
			doc += line[i];
		}

		try {
			out = new FileOutputStream(prefInfo);
			byte bt[] = new byte[1024];
			bt = doc.getBytes();
			out.write(bt);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Scope.getInstace().setPrefInfo(pref);
		Scope.getInstace().getPrefStage().hide();
	}
}