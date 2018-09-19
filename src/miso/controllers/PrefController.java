package miso.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import miso.component.pref.PrefListView;
import miso.utility.Scope;
import miso.views.*;

public class PrefController implements Initializable {

	@FXML
	PrefListView listView;
	@FXML
	BorderPane pane;

	ObservableList<String> items = FXCollections.observableArrayList("Dictionary", "Encyclopedia", "Image", "Video",
			"Translate", "Map", "Hot Key", "Ocr");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		listView.setItems(items);
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				String str = listView.getSelectionModel().getSelectedItem();
				switch (str) {
				case "Dictionary":
					pane.setRight(Scope.getInstace().getDicBorder());
					break;
				case "Encyclopedia":
					pane.setRight(Scope.getInstace().getEncBorder());
					break;
				case "Image":
					pane.setRight(Scope.getInstace().getImgBorder());
					break;
				case "Map":
					pane.setRight(Scope.getInstace().getMapBorder());
					break;
				case "Translate":
					pane.setRight(Scope.getInstace().getTrsBorder());
					break;
				case "Video":
					pane.setRight(Scope.getInstace().getVideoBorder());
					break;
				case "Ocr":
					pane.setRight(Scope.getInstace().getOcrBorder());
					break;
				case "Hot Key":
					pane.setRight(Scope.getInstace().getHotkeyBorder());
					break;
				}
			}

		});
	}

}