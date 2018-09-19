package miso.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PrefBorder {
	protected BorderPane border;
	protected String fxml;
	protected String name;
	
	public PrefBorder() {
		// TODO Auto-generated constructor stub
	}

	public void load() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		
		try {
			border = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BorderPane getBorder() {
		return border;
	}

	public void setBorder(BorderPane border) {
		this.border = border;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
