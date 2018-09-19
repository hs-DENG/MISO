package miso;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import miso.hook.MISOKeyHook;
import miso.hook.MISOMouseHook;
import miso.utility.Scope;
import miso.utility.TrayIconHandler;
import miso.views.PrefView;
import miso.views.SettingView;

public class Main extends Application {
   private static TrayIcon trayIcon;


   @Override
   public void start(Stage primaryStage) {

      SettingView settingView = new SettingView();
      settingView.load();
   }

   public static void main(String[] args) {
      TrayIconHandler.registerTrayIcon(Toolkit.getDefaultToolkit().getImage("tray/misoTray.png"), "MISO",
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                        if (!Scope.getInstace().getSettingStage().isShowing())
                           Scope.getInstace().getSettingStage().show();
                     }
                  });
                  Platform.setImplicitExit(false);
               }
            });
      TrayIconHandler.addMenu("Actions");
      TrayIconHandler.addItemToMenu("Actions", "Show", new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {

               @Override
               public void run() {
                  if (!Scope.getInstace().getSettingStage().isShowing()) {
                     Scope.getInstace().getSettingStage().show();
                  }
               }
            });
            Platform.setImplicitExit(false);

         }
      });
      TrayIconHandler.addItemToMenu("Actions", "Hide", new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {

               @Override
               public void run() {
                  if (Scope.getInstace().getSettingStage().isShowing()) {
                     Scope.getInstace().getSettingStage().hide();
                  }

               }
            });
            Platform.setImplicitExit(false);
         }
      });
      TrayIconHandler.addItem("Option", new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {

               @Override
               public void run() {
                  PrefView prefView = new PrefView();
                  prefView.load();
               }
            });
            Platform.setImplicitExit(false);
         }
      });
      TrayIconHandler.addItem("Exit", new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Platform.runLater(new Runnable() {

               @Override
               public void run() {
                  System.exit(0);
               }
            });
            Platform.setImplicitExit(false);
         }
      });

      launch(args);
   }

   public static void registerTrayIcon(Image image, String toolTip, ActionListener action) {
      if (SystemTray.isSupported()) {
         if (trayIcon != null) {
            trayIcon = null;
         }
         trayIcon = new TrayIcon(image);
         trayIcon.setImageAutoSize(true);

         if (toolTip != null) {
            trayIcon.setToolTip(toolTip);
         }

         if (action != null) {
            trayIcon.addActionListener(action);
         }

         try {
            for (TrayIcon registeredTrayIcon : SystemTray.getSystemTray().getTrayIcons()) {
               SystemTray.getSystemTray().remove(registeredTrayIcon);
            }

            SystemTray.getSystemTray().add(trayIcon);
         } catch (AWTException e) {
            // LOGGER.error("I got catch an error during add system tray !", e);
         }
      } else {
         // LOGGER.error("System tray is not supported !");
      }
   }

}