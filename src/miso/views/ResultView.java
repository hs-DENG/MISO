package miso.views;

import java.io.IOException;
import java.util.Vector;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import miso.actions.SearchModule;
import miso.component.setting.SettingButton;
import miso.utility.Scope;

public class ResultView extends View {
	protected FXMLLoader loader;
	protected TabPane root;
	private String fxml = "/miso/fxml/resultView.fxml";
	private Vector<Tab> tabs = new Vector<Tab>();
	
	public ResultView() {
	}

	@Override
	public void load() {
		try {
			primaryStage = new Stage();

			loader = new FXMLLoader(getClass().getResource(fxml));

			root = loader.load();

			scene = new Scene(root, 600, 900);
			
			Scope.getInstace().setResultStage(primaryStage);
			primaryStage.getIcons().add(new Image("/miso/icon/miso.png"));
			primaryStage.setTitle("result");
			primaryStage.setScene(scene);
			primaryStage.show();
			//setResize();
			
			//TODO title icon setting
			primaryStage.setOnCloseRequest(e->{
				for(int i=0;i<tabs.size();i++) {
					tabs.get(i).setContent(null);
				
				}
			});
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TabPane getRoot() {
		return root;
	}

	public void setRoot(TabPane root) {
		this.root = root;
	}
	public Vector<Tab> getTabs() {
		return tabs;
	}

	public void setTabs(Vector<Tab> tabs) {
		this.tabs = tabs;
	}
}
