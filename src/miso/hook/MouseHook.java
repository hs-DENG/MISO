package miso.hook;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;
import miso.actions.SearchModule;

abstract public class MouseHook extends GlobalMouseHook{
	// hook adapter
	protected GlobalMouseAdapter mouseAdapter;
	protected Robot robot;
	private String searchWord = null;
	private Clipboard clipboard = Clipboard.getSystemClipboard();
	
	public MouseHook() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mouseAdapter = new GlobalMouseAdapter() {
			@Override
			public void mousePressed(GlobalMouseEvent event) {
//				System.out.println("[" + this + "]" + " mouse Pressed");
				mousePressedAction(event);
			}

			@Override
			public void mouseReleased(GlobalMouseEvent event) {
//				System.out.println("[" + this + "]" + " mouse Released");
				mouseReleasedAction(event);
			}

			@Override
			public void mouseMoved(GlobalMouseEvent event) {
				// System.out.println("[" + this + "]" + " mouse Moved");
				mouseMovedAction(event);
			}

			@Override
			public void mouseWheel(GlobalMouseEvent event) {
//				System.out.println("[" + this + "]" + " mouse Wheeled");
				mouseWheelAction(event);
			}
		};
		
	}

	// abstract method
	abstract public void mousePressedAction(GlobalMouseEvent event);

	abstract public void mouseReleasedAction(GlobalMouseEvent event);

	abstract public void mouseMovedAction(GlobalMouseEvent event);

	abstract public void mouseWheelAction(GlobalMouseEvent event);

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

	// listener add/remove
	public void addListener() {
		addMouseListener(mouseAdapter);
	}

	public void removeListener() {
		removeMouseListener(mouseAdapter);
	}
}
