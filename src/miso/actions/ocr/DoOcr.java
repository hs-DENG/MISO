package miso.actions.ocr;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import miso.actions.SearchModule;
import miso.utility.Scope;
import miso.views.OcrView;

public class DoOcr {
	private Scope scope = Scope.getInstace();
	private OcrView ocrView;

	public void ocrAction() {
		if (SearchModule.getInstance().getActionCount() == 0) {

//			scope.getSettingStage().setAlwaysOnTop(false);

			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("WARNING");
					alert.setHeaderText(null);
					alert.setContentText("검색할 카테고리를 선택하세요.");
					alert.setResizable(false);
					alert.show();

				}
			});
			return;
		}

		// on 했을 때`
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// scope.setOcrFlag(true);
				ocrView = new OcrView();
				ocrView.load();
				System.out.println("OCR View Load Success");
				scope.setOcrFlag(true);
			}
		});
		Platform.setImplicitExit(false);

	}
}