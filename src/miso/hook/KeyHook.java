package miso.hook;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import miso.actions.ocr.DoOcr;
import miso.utility.Scope;
import miso.actions.SearchModule;

abstract public class KeyHook extends GlobalKeyboardHook {
	// hook adapter
	protected GlobalKeyAdapter keyAdapter;
	protected Robot robot;
	private String searchWord = null;
	private Clipboard clipboard = Clipboard.getSystemClipboard();
	private DoOcr doOcr = null;
	public KeyHook() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyAdapter = new GlobalKeyAdapter() {
			@Override
			public void keyPressed(GlobalKeyEvent event) {
				keyPressedAction(event);
			}

			@Override
			public void keyReleased(GlobalKeyEvent event) {
				keyReleasedAction(event);
			}
		};
	}

	// abstract method
	abstract public void keyPressedAction(GlobalKeyEvent event);

	abstract public void keyReleasedAction(GlobalKeyEvent event);

	protected void hookAction() {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				searchWord = clipboard.getString();
				System.out.println(searchWord + "is search word");
				SearchModule searchModule = SearchModule.getInstance();
				searchModule.action(searchWord);
				
			}
		});
		Platform.setImplicitExit(false);
	}

	protected void ocrHookAction() {
		doOcr = new DoOcr();
		doOcr.ocrAction();
	}

	// listener add/remove
	public void addListener() {
		addKeyListener(keyAdapter);
	}

	public void removeListener() {
		removeKeyListener(keyAdapter);
	}
}