package miso.component.setting;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import miso.utility.Scope;

public class MisoButton extends JFXButton {
   private Scope scope = Scope.getInstace();
   private boolean clicked = false;

   public MisoButton() {
    
      addEventHandler(ActionEvent.ACTION, e -> {
         action(e);
      });
   }

   protected void action(ActionEvent e) {
      if (!clicked) {
         Pane pane = scope.getTextFieldPane();
         pane.setVisible(true);
         Pane ocrPane = scope.getOcrPane();
         ocrPane.setVisible(true);
         clicked = true;
      } else {
         Pane pane = scope.getTextFieldPane();
         pane.setVisible(false);
         Pane ocrPane = scope.getOcrPane();
         ocrPane.setVisible(false);
         clicked = false;
      }
   }
}