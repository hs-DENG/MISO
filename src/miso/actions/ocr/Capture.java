package miso.actions.ocr;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import miso.utility.Scope;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Capture {
	// scope
	private Scope scope = Scope.getInstace();

	// 드래그 영역 시작점, 끝점
	private Point startPoint = new Point(0, 0);
	private Point lastPoint = new Point(0, 0);

	// 캡쳐 영역
	private Rectangle captureArea;

	// buffered image
	private BufferedImage bufferedImage;
	private ITesseract instance = new Tesseract();

	public Capture() {
		instance.setLanguage("kor");
		captureArea = new Rectangle();
	}

	public void capture() {
		try {
			String inDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
			Robot robot = new Robot();
			bufferedImage = robot.createScreenCapture(captureArea);
			File path = new File("./ocr/ocr.jpg");

			ImageIO.write(bufferedImage, "jpg", path);
			System.out.println("capture success");

			File imageFile = new File("./ocr/ocr.jpg");

			// JNA Interface Mapping
			// ITesseract instance = new Tesseract1(); // JNA Direct Mapping
			// instance.setDatapath("<parentPath>"); // replace <parentPath> with path to
			// parent directory of tessdata

			// 디폴트는 영어 디텍트

			String result = instance.doOCR(imageFile);
			System.out.println("dectection success :" + result);
			scope.setDetectedWord(result);
		} catch (AWTException | IOException | TesseractException e) {
			e.printStackTrace();
		}
	}

	public void settingLanguage(String language) {
		switch (language) {
		case "Korean":
			instance.setLanguage("kor");
			System.out.println("ocr language kor");
			break;
		case "English":
			instance.setLanguage("eng");
			System.out.println("ocr language eng");
			break;
		}
	}

	public void setCaptureArea() {
		int startX, startY;
		int width, height;
		if (startPoint.getX() < lastPoint.getX()) {
			startX = (int) startPoint.getX();
			width = (int) (lastPoint.getX() - startPoint.getX());
		} else {
			startX = (int) lastPoint.getX();
			width = (int) (startPoint.getX() - lastPoint.getX());
		}
		if (startPoint.getY() < lastPoint.getY()) {
			startY = (int) startPoint.getY();
			height = (int) (lastPoint.getY() - startPoint.getY());
		} else {
			startY = (int) lastPoint.getY();
			height = (int) (startPoint.getY() - lastPoint.getY());
		}
		captureArea.setBounds(startX - 5, startY + 10, width, height);
	}

	public double getArea() {
		return captureArea.getWidth() * captureArea.getHeight();
	}

	// getter / setter

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(Point lastPoint) {
		this.lastPoint = lastPoint;
	}

	public Rectangle getRectArea() {
		return captureArea;
	}

	public void setRectArea(Rectangle rectArea) {
		this.captureArea = rectArea;
	}
}