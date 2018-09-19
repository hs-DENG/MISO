package miso.component.result;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.web.WebView;

public class ResultTab extends Tab{
	public ResultTab() {
		super();
	}
	public ResultTab(String title, Node node) {
		super(title, node);
	}
	public void setListener(WebView browser) {
		setOnCloseRequest(e -> {
			browser.getEngine().load(null);
		});
	}
}
