package miso.component.result;

import java.net.URLEncoder;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import miso.actions.Translater;
import miso.utility.Scope;

public class TranslateButton extends JFXButton {
   private Scope scope = Scope.getInstace();
   private String from,to;
   
   public TranslateButton() {
      addEventHandler(ActionEvent.ACTION, e -> {
         action();
      });
   }
   
   
   
   private void action() {
      setTlsOption();
      String originalText = scope.getSearchKey().getText();
   
      try {
         Translater translater = new Translater();
         //String text = URLEncoder.encode(originalText, "UTF-8");
      
         String translatedText = translater.translate(originalText, from ,to);

         scope.getSearchKey().setText(originalText);
         scope.getTlsResult().setText(translatedText);
      }
      catch (Exception e) {
         // TODO: handle exception
      }
   }


   public void setTlsOption() {
      Pane pane = (Pane)getParent();
      ChoiceBox from2 = (ChoiceBox)pane.getChildren().get(2);
      ChoiceBox to2 = (ChoiceBox)pane.getChildren().get(3);
      from =(String) from2.getValue();
      to = (String) to2.getValue();
      scope.setTrsFrom(from);
      scope.setTrsTo(to);

   }
}
