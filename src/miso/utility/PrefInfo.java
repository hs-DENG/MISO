package miso.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import lc.kra.system.mouse.event.GlobalMouseEvent;
import miso.hook.MISOMouseHook;
import miso.resource.InputVirtualKeys;

public class PrefInfo {
   private String dicAPI;
   private String encAPI;
   private String imgAPI;
   private String videoAPI;
   private String trsAPI;
   private String mapAPI;
   private String ocrLanguage;
   
   private int hotKey;
   private int ocrKey;
   private int mouse;

   private Scope scope = Scope.getInstace();
   private static final String prefInfo = "./preference/prefInfo.txt";
   private FileInputStream in = null;


   public PrefInfo() {
      try {
         File prefFile = new File(prefInfo);
         in = new FileInputStream(prefFile);
         byte bt[] = new byte[(int) prefFile.length()];

         in.read(bt);
         String doc = new String(bt);
         String[] line = doc.split("\n");
         for (int i = 0; i < line.length; i++) {
            String[] info = line[i].split(":");

            switch (info[0].trim()) {
            case "dic":
               dicAPI = info[1].trim();
               break;
            case "enc":
               encAPI = info[1].trim();
               break;
            case "img":
               imgAPI = info[1].trim();
               break;
            case "video":
               videoAPI = info[1].trim();
               break;
            case "trs":
               trsAPI = info[1].trim();
               break;
            case "map":
               mapAPI = info[1].trim();
               break;
            case "ocr":
               ocrKey = Integer.parseInt(info[1].trim());
               break;
            case "hot":
               hotKey = Integer.parseInt(info[1].trim());
               break;
            case "ocrLanguage" : 
            	ocrLanguage =info[1].trim();
            	break;
            	
      
            }
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void setDefault() {

      dicAPI = "Naver";
      encAPI = "Naver";
      imgAPI = "Naver";
      videoAPI = "Youtube";
      trsAPI = "Naver";
      mapAPI = "Google";
     
      ocrKey = InputVirtualKeys.KeyBoard_Shift;
      mouse = GlobalMouseEvent.BUTTON_MIDDLE;

      ocrLanguage = "Korean";
   }

   public void load() {

      scope.getDicButton().setAPI(dicAPI);
      scope.getEncButton().setAPI(encAPI);
      scope.getImgButton().setAPI(imgAPI);
      scope.getVideoButton().setAPI(videoAPI);
      scope.getTlsButton().setAPI(trsAPI);
      scope.getMapButton().setAPI(mapAPI);

      // TODO set OCR language

   }

   public String getDicAPI() {
      return dicAPI;
   }

   public void setDicAPI(String dicAPI) {
      this.dicAPI = dicAPI;
   }

   public String getEncAPI() {
      return encAPI;
   }

   public void setEncAPI(String encAPI) {
      this.encAPI = encAPI;
   }

   public String getImgAPI() {
      return imgAPI;
   }

   public void setImgAPI(String imgAPI) {
      this.imgAPI = imgAPI;
   }

   public String getVideoAPI() {
      return videoAPI;
   }

   public void setVideoAPI(String videoAPI) {
      this.videoAPI = videoAPI;
   }

   public String getTrsAPI() {
      return trsAPI;
   }

   public void setTrsAPI(String tlsAPI) {
      this.trsAPI = tlsAPI;
   }

   public String getMapAPI() {
      return mapAPI;
   }

   public void setMapAPI(String mapAPI) {
      this.mapAPI = mapAPI;
   }

   public int getMouse() {
      return mouse;
   }

   public void setMouse(int mouse) {
      this.mouse = mouse;
   }

   public String getOcrLanguage() {
      return ocrLanguage;
   }

   public void setOcrLanguage(String ocrLanguage) {
      this.ocrLanguage = ocrLanguage;
   }

   public int getHotKey() {
      return hotKey;
   }

   public void setHotKey(int hotKey) {
      this.hotKey = hotKey;
   }

   public int getOcrKey() {
      return ocrKey;
   }

   public void setOcrKey(int ocrKey) {
      this.ocrKey = ocrKey;
   }

}