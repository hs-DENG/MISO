package miso.views;

import java.io.IOException;

import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import miso.utility.Scope;

public class TrsTabView extends Tab {
	private String fxml = "/miso/fxml/trsTab.fxml";
	private JFXTabPane root;

	public TrsTabView() {

	}

	public void load() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			root = loader.load();
			AnchorPane anchorPane = (AnchorPane) root.getTabs().get(0).getContent();
			Pane pane = (Pane) anchorPane.getChildren().get(0);
			ChoiceBox cboxFrom = (ChoiceBox) pane.getChildren().get(1);
			ChoiceBox cboxTo = (ChoiceBox) pane.getChildren().get(2);
			String from = Scope.getInstace().getTrsFrom();
			Scope scope = Scope.getInstace();
			switch (from) {
			case "ko":
				from = "Korean";
				break;
			case "ja":
				from = "Japanese";
				break;
			case "zh-CN":
				from = "Chinese";
				break;
			case "eh":
				from = "English";
				break;
			}
			cboxFrom.setValue(from);
		
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

}