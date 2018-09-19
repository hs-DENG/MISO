package miso.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import miso.utility.Scope;

public class TrsController implements Initializable{
   @FXML
   private TextArea searchKey;
   @FXML
   private Text tlsResult; 
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      Scope scope = Scope.getInstace();
      scope.setSearchKey(searchKey);
      scope.setTlsResult(tlsResult);
      
   }

}