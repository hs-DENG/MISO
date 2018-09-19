package miso.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import miso.actions.SearchModule;
import miso.actions.ocr.Capture;
import miso.actions.ocr.MyCanvas;
import miso.component.setting.DicButton;
import miso.component.setting.EncButton;
import miso.component.setting.ImgButton;
import miso.component.setting.MapButton;
import miso.component.setting.MisoButton;
import miso.component.setting.PrefButton;
import miso.component.setting.SearchButton;
import miso.component.setting.TrsButton;
import miso.component.setting.VideoButton;
import miso.utility.Scope;
import miso.views.SettingView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXNodesList;

import javafx.animation.Interpolator;
import javafx.animation.KeyValue;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class SettingController implements Initializable {

	@FXML
	private AnchorPane parent;
	@FXML
	private EncButton encButton;
	@FXML
	private ImgButton imgButton;
	@FXML
	private MapButton mapButton;
	@FXML
	private DicButton dicButton;

	@FXML
	private VideoButton videoButton;
	@FXML
	private TrsButton tlsButton;
	@FXML
	private JFXTextField searchTextfield;
	@FXML
	private SearchButton searchButton;

	@FXML
	private PrefButton prefButton;
	@FXML
	private Pane ocrPane;
	@FXML
	private JFXToggleButton ocrToggleButton;
	private boolean ocrToggleFlag = false;// ocrtoggle버튼이 on인지 off인지 구분
	// ------------------
	@FXML
	private Pane pane;

	private boolean textFeildFlag = false;
	private Capture capture = new Capture();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Scope scope = Scope.getInstace();

		parent.setStyle("-fx-background-color: transparent;");
		parent.setMinHeight(com.sun.glass.ui.Screen.getMainScreen().getHeight());
		parent.setMinWidth(com.sun.glass.ui.Screen.getMainScreen().getWidth());

		// connect
		scope.setDicButton(dicButton);
		scope.setEncButton(encButton);
		scope.setImgButton(imgButton);
		scope.setMapButton(mapButton);
		scope.setVideoButton(videoButton);
		scope.setTlsButton(tlsButton);

		scope.setSearchButton(searchButton);
		scope.setSearchTextField(searchTextfield);
		scope.setPreferenceButton(prefButton);
		scope.setTextFieldPane(pane);
		scope.setCapture(capture);

		pane.setVisible(false);
		// reverse
		dicButton.setScope(scope);
		encButton.setScope(scope);
		imgButton.setScope(scope);
		mapButton.setScope(scope);
		videoButton.setScope(scope);
		tlsButton.setScope(scope);

		searchButton.setScope(scope);
		prefButton.setScope(scope);

		searchTextfield.setOnAction(e -> {
			String keyword = searchTextfield.getText();
			SearchModule.getInstance().action(keyword);
		});
		// Pane pane = new Pane();
		// pane.setPrefSize(600, 400);
		pane.setTranslateX(com.sun.glass.ui.Screen.getMainScreen().getWidth() - 500);
		pane.setTranslateY(com.sun.glass.ui.Screen.getMainScreen().getHeight() - 100);

		scope.setOcrToggleFlag(ocrToggleFlag);
		ocrPane.setTranslateX(com.sun.glass.ui.Screen.getMainScreen().getWidth() - 265);
		ocrPane.setTranslateY(com.sun.glass.ui.Screen.getMainScreen().getHeight() - 150);
		ocrPane.setVisible(false);
		scope.setOcrPane(ocrPane);
		// prefButton.setTranslateX(com.sun.glass.ui.Screen.getMainScreen().getWidth()-570);
		// prefButton.setTranslateY(com.sun.glass.ui.Screen.getMainScreen().getHeight()-175);
		JFXNodesList nodeList1 = new JFXNodesList();

		MisoButton misoButton = new MisoButton();
		misoButton.setStyle("-fx-graphic: url('/miso/icon/miso.png')");
		ocrToggleButton.setText("OCR Mode");
		// ocrToggleButton.setTranslateX(1650);
		// ocrToggleButton.setTranslateY(930);
		ocrToggleButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (ocrToggleButton.isSelected() == true) {
					ocrToggleFlag = true;
					scope.setOcrToggleFlag(ocrToggleFlag);
				} else {
					ocrToggleFlag = false;
					scope.setOcrToggleFlag(ocrToggleFlag);
				}
			}

		});
		scope.setOcrToggleButton(ocrToggleButton);

		
		nodeList1.setTranslateX(com.sun.glass.ui.Screen.getMainScreen().getWidth() - 95);
		nodeList1.setTranslateY(80);

		nodeList1.addAnimatedNode(misoButton);
		nodeList1.addAnimatedNode(dicButton);
		nodeList1.addAnimatedNode(encButton);
		nodeList1.addAnimatedNode(imgButton);
		nodeList1.addAnimatedNode(mapButton);
		nodeList1.addAnimatedNode(videoButton);
		nodeList1.addAnimatedNode(tlsButton);
		nodeList1.addAnimatedNode(prefButton);

		// nodeList1.addAnimatedNode(searchTextfield);
		nodeList1.setRotate(-180);
		parent.getChildren().add(nodeList1);
		nodeList1.setSpacing(10d);

		// AnchorPane.setRightAnchor(nodeList1, parent.getPrefWidth()/2);
		AnchorPane.setTopAnchor(nodeList1, parent.getPrefWidth());

		// AnchorPane.setBottomAnchor(nodeList1, parent.getPrefHeight()/2);
	}

}
