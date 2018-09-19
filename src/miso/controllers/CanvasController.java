package miso.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import miso.actions.ocr.MyCanvas;
import miso.utility.Scope;

public class CanvasController implements Initializable {
   private Scope scope = Scope.getInstace();
   
   @FXML
   private MyCanvas myCanvas;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      // TODO Auto-generated method stub
      scope.setMyCanvas(myCanvas);
   }

}