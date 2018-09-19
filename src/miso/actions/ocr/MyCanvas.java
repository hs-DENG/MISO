package miso.actions.ocr;

import java.awt.Point;
import java.awt.Rectangle;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import miso.actions.SearchModule;
import miso.utility.Scope;

public class MyCanvas extends Canvas {
   private GraphicsContext gc = this.getGraphicsContext2D();
   private Scope scope = Scope.getInstace();
   private MyCanvas myCanvas = this;
   private Capture capture = scope.getCapture();
   public MyCanvas() {
      this.setTranslateX(0);
      this.setTranslateY(0);
      this.setWidth(1920);
      this.setHeight(1080);
      addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
         mousePressed(e);
      });

      addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
         mouseDragged(e);
      });

      addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
         mouseReleased(e);
      });

   }

   public void mousePressed(MouseEvent e) {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            scope.getSettingStage().hide();
            if (scope.getPrefStage() != null) {
               scope.getPrefStage().hide();
            }
            scope.getOcrStage().show();
            capture.setStartPoint(new Point((int) e.getX(), (int) e.getY()));
         }
      });
      Platform.setImplicitExit(false);
      System.out.println("mousePressed on canvas");
      // System.out.println(capture.getStartPoint().getX() +" , "
      // +capture.getStartPoint().getY() );
   }

   public void mouseReleased(MouseEvent e) {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            scope.getSettingStage().show();
            /*
             * if (scope.getPrefStage() != null) { scope.getPrefStage().show(); }
             */
            scope.getOcrStage().hide();
        

            if (capture.getArea() > 100) {
               try {
                  Thread.sleep(100);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               scope.getOcrStage().setOpacity(0.001);
               gc.setFill(Color.web("white", 0.001));
               gc.fillRect(capture.getStartPoint().getX(), capture.getStartPoint().getY(),
                     capture.getRectArea().getWidth(), capture.getRectArea().getHeight());
               capture.capture();
               capture.setRectArea(new Rectangle(0, 0, 0, 0));
               gc.clearRect(0, 0, 1920, 1080);

               //scope.getSearchTextField().setText(scope.getDetectedWord());
               
               SearchModule searchModule = SearchModule.getInstance();
               searchModule.action(scope.getDetectedWord());
            }
         }
      });
      Platform.setImplicitExit(false);
      System.out.println("mouseReleased on canvas");
   }

   public void mouseDragged(MouseEvent e) {
      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            capture.setLastPoint(new Point((int) e.getX(), (int) e.getY()));

            capture.setCaptureArea();

            gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

            gc.setLineWidth(2);
            gc.setStroke(Color.web("red", 1));
            gc.strokeRect(capture.getRectArea().getX()+5, capture.getRectArea().getY()-10,
                  capture.getRectArea().getWidth(), capture.getRectArea().getHeight());

            gc.setFill(Color.web("white", 0.00001));
            gc.fillRect(capture.getStartPoint().getX()+5, capture.getStartPoint().getY()-10,
                  capture.getRectArea().getWidth(), capture.getRectArea().getHeight());
         }
      });
      Platform.setImplicitExit(false);
   }

}