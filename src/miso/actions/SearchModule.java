package miso.actions;

import java.util.Vector;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TabPane;

import miso.component.setting.SettingButton;
import miso.utility.Scope;
import miso.utility.TabContent;
import miso.views.ResultView;

public class SearchModule {
	private static Vector<SettingButton> actionVector = new Vector<SettingButton>();

	private static SearchModule searchModule = new SearchModule();

	private SearchModule() {
	}

	public static SearchModule getInstance() {
		return searchModule;
	}

	public void action(String keyword) {
		if (actionVector.size() != 0) {

			ResultView resultView = new ResultView();
			resultView.load();
			TabPane root = resultView.getRoot();

			for (int i = 0; i < actionVector.size(); i++) {
				TabContent tabContent = new TabContent();

				actionVector.elementAt(i).makeHtml(keyword);
				actionVector.elementAt(i).search(keyword, tabContent.getBrowser());
				tabContent.setTab(actionVector.elementAt(i).getTab());
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						tabContent.getBrowser().requestFocus();
								
					}
				});Platform.setImplicitExit(false);
				
				resultView.getTabs().add(actionVector.elementAt(i).getTab());
				root.getTabs().add(tabContent.getTab());
				
				Scope.getInstace().getResultStage().setOnCloseRequest(e -> {
					tabContent.getBrowser().getEngine().load(null);
				});
			}
			Scope.getInstace().getSearchTextField().setText(keyword);
			
		} else {

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText("검색할 카테고리를 선택하세요.");
			alert.setResizable(false);
			alert.show();

		}
	}

	public void addActionVector(SettingButton comboJFXButton) {
		if (!actionVector.contains(comboJFXButton)) {
			actionVector.add(comboJFXButton);
		}
	}

	public void removeActionVector(SettingButton searchAction) {
		actionVector.remove(searchAction);
	}

	public void clearSearchAction() {
		for (int i = 0; i < actionVector.size(); i++) {
			actionVector.get(i).actionOff();
			// 효과 등등 off
		}
		actionVector.clear();
	}

	public int getActionCount() {
		return actionVector.size();
	}

	public Vector<SettingButton> getActionVector() {
		return actionVector;
	}

	public void setActionVector(Vector<SettingButton> actionVector) {
		this.actionVector = actionVector;
	}
}
