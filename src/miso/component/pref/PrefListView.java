package miso.component.pref;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import miso.views.*;

public class PrefListView extends JFXListView<String> {
	private int index;

	private JFXListView<String> settingListView = new JFXListView<String>();
	ObservableList<String> items = FXCollections.observableArrayList("Dictionary", "Encyclopedia", "Image", "Map",
			"Translate", "Video", "Hotkey","Ocr");

	public PrefListView() {

	}

	public void makeListView() {

		settingListView.setItems(items);
		settingListView.setStyle("-fx-background-color: #FFBB00;");
	}
}