package miso.views;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

abstract public class View {
	protected Stage primaryStage;
	protected Scene scene;

	public View() {
	}
	public void setResize() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				primaryStage.setResizable(false);
			}
		});
	}
	abstract public void load();

}
