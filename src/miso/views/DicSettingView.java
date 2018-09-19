package miso.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class DicSettingView extends PrefBorder{
   
   public DicSettingView() {
	   fxml = "/miso/fxml/dicsetting.fxml";
	   name = "dic";
   }

   public void load() {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
      try {
         border = loader.load();

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}