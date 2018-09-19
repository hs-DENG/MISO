package miso.component.setting;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleNode;

import javafx.event.ActionEvent;
import miso.utility.Scope;
import miso.views.PrefView;

public class PrefButton extends JFXButton {
	private Scope scope;

	public PrefButton() {
		addEventHandler(ActionEvent.ACTION, e -> {
			if (!scope.isPrefViewFlag()) {
				PrefView prefView = new PrefView();
				prefView.load();
			}
		});
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

}
