package miso.component.pref;



import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import miso.utility.Scope;

public class RestoreDefaultsButton extends JFXButton{
	public RestoreDefaultsButton() {
		addEventHandler(ActionEvent.ACTION, e -> {
			Scope.getInstace().getPrefInfo().setDefault();
			//TODO UI ¹Ù²î±â
			Scope.getInstace().setPrefViewFlag(false);
		});
	}
}
