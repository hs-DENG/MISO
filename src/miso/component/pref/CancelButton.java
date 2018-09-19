package miso.component.pref;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import miso.utility.Scope;

public class CancelButton extends JFXButton {
	private Scope scope = Scope.getInstace();

	public CancelButton() {
		addEventHandler(ActionEvent.ACTION, e -> {
			cancel(e);
		});
	}

	private void cancel(ActionEvent e) {
		System.out.println("cancel");
		Scope.getInstace().getPrefStage().hide();
		scope.setPrefViewFlag(false);
	}
}