package miso.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import miso.actions.ocr.MyCanvas;
import miso.utility.Scope;

public class OcrView extends View {
   private FXMLLoader loader;
   private String fxml = "/miso/fxml/ocrView.fxml";
   private Scope scope = Scope.getInstace();
   private AnchorPane root;

   @Override
   public void load() {
      try {
         primaryStage = new Stage();
         loader = new FXMLLoader(getClass().getResource(fxml));
         root = loader.load();
         scene = new Scene(root, com.sun.glass.ui.Screen.getMainScreen().getWidth(),
               com.sun.glass.ui.Screen.getMainScreen().getHeight());
         primaryStage.setAlwaysOnTop(true);
         primaryStage.setScene(scene);
         primaryStage.setOpacity(0.5);
         scope.getSettingStage().hide();
         primaryStage.show();
         scope.setOcrStage(primaryStage);
         setResize();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public AnchorPane getRoot() {
      return root;
   }

   public void setRoot(AnchorPane root) {
      this.root = root;
   }

}