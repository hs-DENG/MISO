package miso.views;

import java.io.IOException;
import java.util.Vector;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import miso.component.setting.SettingButton;
import miso.hook.MISOMouseHook;
import miso.utility.Scope;

public class SettingView extends View {
	static private Vector<SettingButton> comboVector = new Vector<SettingButton>();
	private FXMLLoader loader;
	private AnchorPane root;
	private final String fxml = "/miso/fxml/settingView.fxml";

	public SettingView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		try {
			primaryStage = new Stage();

			loader = new FXMLLoader(getClass().getResource(fxml));
			root = loader.load();
			scene = new Scene(root,com.sun.glass.ui.Screen.getMainScreen().getWidth(),
		               com.sun.glass.ui.Screen.getMainScreen().getHeight(),Color.TRANSPARENT);
			
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setTitle("Setting");
//			primaryStage.getIcons().add(new Image("/miso/icon/misoTray.png"));
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
			
			Scope.getInstace().setSettingStage(primaryStage);
			
			primaryStage.setOnCloseRequest(e -> {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						Scope.getInstace().setSettingStage(primaryStage);
					}
				});
				Platform.setImplicitExit(false);
			});
			setResize();
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Vector<SettingButton> getComboVector() {
		return comboVector;
	}

	public void setComboVector(Vector<SettingButton> comboVector) {
		this.comboVector = comboVector;
	}
}