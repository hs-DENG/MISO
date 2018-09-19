package miso.hook;

import javafx.application.Platform;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import miso.utility.Scope;

public class MISOKeyHook extends KeyHook {

	@Override
	public void keyPressedAction(GlobalKeyEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getVirtualKeyCode()+"press");
		// TODO OCR
		if (Scope.getInstace().getPrefInfo().getOcrKey() == event.getVirtualKeyCode()
				&& !Scope.getInstace().isOcrFlag()) {
			ocrHookAction();
			Scope.getInstace().setOcrFlag(true);
		}
	}

	@Override
	public void keyReleasedAction(GlobalKeyEvent event) {
		System.out.println(event.getVirtualKeyCode()+"release");
		if (Scope.getInstace().getPrefInfo().getHotKey() == event.getVirtualKeyCode()) {
			hookAction();
		}
		Scope.getInstace().setOcrFlag(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (Scope.getInstace().getOcrStage() != null)
					Scope.getInstace().getOcrStage().hide();
			}
		});
	}
}