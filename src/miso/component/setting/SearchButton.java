package miso.component.setting;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.scene.input.Clipboard;
import miso.actions.SearchModule;
import miso.utility.Scope;

public class SearchButton extends JFXButton {
	private Scope scope;
	
	public SearchButton() {

		
		
		setFocused(false);
		addEventHandler(ActionEvent.ACTION, e-> {
			// ctrl + mL 액션과 동
			String keyword = null;
			scope = Scope.getInstace();
			System.out.println(scope.getSearchTextField());
			 System.out.println(scope.getSearchTextField().getText());
			keyword = scope.getSearchTextField().getText();
			
			SearchModule searchModule = SearchModule.getInstance();
			searchModule.action(keyword);
			setFocused(false);
		});
	}
	
	
	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}
	
}
