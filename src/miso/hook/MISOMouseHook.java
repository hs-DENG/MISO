package miso.hook;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import miso.actions.SearchModule;
import miso.utility.Scope;

public class MISOMouseHook extends MouseHook {
	public MISOMouseHook() {
	}

	@Override
	public void mousePressedAction(GlobalMouseEvent event) {
	//	System.out.println("mouse press");
		SelectionKey hi = new SelectionKey() {

			@Override
			public Selector selector() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int readyOps() {
				// 

				return 0;
			}

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public SelectionKey interestOps(int ops) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int interestOps() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public SelectableChannel channel() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void cancel() {
				// TODO Auto-generated method stub

			}
		};

	//	System.out.println(hi.toString());
	//	System.out.println("end");
	}

	@Override
	public void mouseReleasedAction(GlobalMouseEvent event) {

		if (Scope.getInstace().getPrefInfo().getMouse() == event.getButton()) {
			hookAction();
		}
	}

	@Override
	public void mouseMovedAction(GlobalMouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelAction(GlobalMouseEvent event) {
		// TODO Auto-generated method stub

	}

}
