package miso.utility;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Vector;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import miso.actions.ocr.Capture;
import miso.actions.ocr.MyCanvas;
import miso.component.setting.DicButton;
import miso.component.setting.EncButton;
import miso.component.setting.ImgButton;
import miso.component.setting.MapButton;
import miso.component.setting.PrefButton;
import miso.component.setting.SearchButton;
import miso.component.setting.TrsButton;
import miso.component.setting.VideoButton;
import miso.hook.MISOKeyHook;
import miso.hook.MISOMouseHook;

public class Scope {

	private static Scope scope = new Scope();

	public static Scope getInstace() {
		return scope;
	}

	// component
	private EncButton encButton;
	private ImgButton imgButton;
	private VideoButton videoButton;
	private MapButton mapButton;
	private TrsButton tlsButton;
	private SearchButton searchButton;
	private PrefButton preferenceButton;
	private JFXTextField searchTextField;
	private DicButton dicButton;
	private TextArea searchKey;
	private Text tlsResult;

	// view
	private Stage settingStage;
	private Stage prefStage;
	private Stage canvasStage;
	private Stage ocrStage;
	private Stage resultStage;
	private MyCanvas myCanvas;

	// Ocr
	private boolean ocrFlag = false;
	private String detectedWord = null;
	private Capture capture;

	// hook
	private MISOMouseHook mouseHook = new MISOMouseHook();
	private MISOKeyHook keyHook = new MISOKeyHook();

	// pref
	private PrefInfo prefInfo = new PrefInfo();
	private BorderPane dicBorder;
	private BorderPane encBorder;
	private BorderPane imgBorder;
	private BorderPane hotkeyBorder;
	private BorderPane mapBorder;
	private BorderPane trsBorder;
	private BorderPane videoBorder;
	private BorderPane ocrBorder;
	private boolean prefViewFlag = false;

	private boolean hotkeyClicked;

	private String trsFrom = "English";
	private String trsTo = "Korean";
	private Vector<BorderPane> prefBorder = new Vector<BorderPane>();
	private Pane textFieldPane;

	private boolean ocrToggleFlag;
	private Pane ocrPane;
	private JFXToggleButton ocrToggleButton;

	private Scope() {
		mouseHook.addListener();
		keyHook.addListener();
	}
	// get / set

	public EncButton getEncButton() {
		return encButton;
	}

	public boolean isPrefViewFlag() {
		return prefViewFlag;
	}

	public void setPrefViewFlag(boolean prefViewFlag) {
		this.prefViewFlag = prefViewFlag;
	}

	public Capture getCapture() {
		return capture;
	}

	public void setCapture(Capture capture) {
		this.capture = capture;
	}

	public BorderPane getOcrBorder() {
		return ocrBorder;
	}

	public void setOcrBorder(BorderPane ocrBorder) {
		this.ocrBorder = ocrBorder;
	}

	public Pane getTextFieldPane() {
		return textFieldPane;
	}

	public void setTextFieldPane(Pane textFieldPane) {
		this.textFieldPane = textFieldPane;
	}

	public Vector<BorderPane> getPrefBorder() {
		return prefBorder;
	}

	public void setPrefBorder(Vector<BorderPane> prefBorder) {
		this.prefBorder = prefBorder;
	}

	public Stage getSettingStage() {
		return settingStage;
	}

	public void setSettingStage(Stage settingStage) {
		this.settingStage = settingStage;
	}

	public Stage getPrefStage() {
		return prefStage;
	}

	public void setPrefStage(Stage prefStage) {
		this.prefStage = prefStage;
	}

	public Stage getCanvasStage() {
		return canvasStage;
	}

	public void setCanvasStage(Stage canvasStage) {
		this.canvasStage = canvasStage;
	}

	public Stage getOcrStage() {
		return ocrStage;
	}

	public void setOcrStage(Stage ocrStage) {
		this.ocrStage = ocrStage;
	}

	public MyCanvas getMyCanvas() {
		return myCanvas;
	}

	public void setMyCanvas(MyCanvas myCanvas) {
		this.myCanvas = myCanvas;
	}

	public boolean isOcrFlag() {
		return ocrFlag;
	}

	public void setOcrFlag(boolean ocrFlag) {
		this.ocrFlag = ocrFlag;
	}

	public String getDetectedWord() {
		return detectedWord;
	}

	public void setDetectedWord(String detectedWord) {
		this.detectedWord = detectedWord;
	}

	public void setEncButton(EncButton encButton) {
		this.encButton = encButton;
	}

	public ImgButton getImgButton() {
		return imgButton;
	}

	public void setImgButton(ImgButton imgButton) {
		this.imgButton = imgButton;
	}

	public VideoButton getVideoButton() {
		return videoButton;
	}

	public void setVideoButton(VideoButton videoButton) {
		this.videoButton = videoButton;
	}

	public SearchButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(SearchButton searchButton) {
		this.searchButton = searchButton;
	}

	public PrefButton getPreferenceButton() {
		return preferenceButton;
	}

	public void setPreferenceButton(PrefButton preferenceButton) {
		this.preferenceButton = preferenceButton;
	}

	public MapButton getMapButton() {
		return mapButton;
	}

	public void setMapButton(MapButton mapButton) {
		this.mapButton = mapButton;
	}

	public JFXTextField getSearchTextField() {
		return searchTextField;
	}

	public void setSearchTextField(JFXTextField searchTextField) {
		this.searchTextField = searchTextField;
	}

	public TrsButton getTlsButton() {
		return tlsButton;
	}

	public void setTlsButton(TrsButton tlsButton) {
		this.tlsButton = tlsButton;
	}

	public DicButton getDicButton() {
		return dicButton;
	}

	public void setDicButton(DicButton dicButton) {
		this.dicButton = dicButton;
	}

	public TextArea getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(TextArea searchKey) {
		this.searchKey = searchKey;
	}

	public Text getTlsResult() {
		return tlsResult;
	}

	public void setTlsResult(Text tlsResult) {
		this.tlsResult = tlsResult;
	}

	public PrefInfo getPrefInfo() {
		return prefInfo;
	}

	public void setPrefInfo(PrefInfo prefInfo) {
		this.prefInfo = prefInfo;
	}

	public boolean isHotkeyClicked() {
		return hotkeyClicked;
	}

	public void setHotkeyClicked(boolean hotkeyClicked) {
		this.hotkeyClicked = hotkeyClicked;
	}

	public MISOMouseHook getMouseHook() {
		return mouseHook;
	}

	public void setMouseHook(MISOMouseHook mouseHook) {
		this.mouseHook = mouseHook;
	}

	public MISOKeyHook getKeyHook() {
		return keyHook;
	}

	public void setKeyHook(MISOKeyHook keyHook) {
		this.keyHook = keyHook;
	}

	public BorderPane getDicBorder() {
		return dicBorder;
	}

	public void setDicBorder(BorderPane dicBorder) {
		this.dicBorder = dicBorder;
	}

	public BorderPane getEncBorder() {
		return encBorder;
	}

	public void setEncBorder(BorderPane encBorder) {
		this.encBorder = encBorder;
	}

	public BorderPane getImgBorder() {
		return imgBorder;
	}

	public void setImgBorder(BorderPane imgBorder) {
		this.imgBorder = imgBorder;
	}

	public BorderPane getHotkeyBorder() {
		return hotkeyBorder;
	}

	public void setHotkeyBorder(BorderPane hotkeyBorder) {
		this.hotkeyBorder = hotkeyBorder;
	}

	public BorderPane getMapBorder() {
		return mapBorder;
	}

	public void setMapBorder(BorderPane mapBorder) {
		this.mapBorder = mapBorder;
	}

	public BorderPane getTrsBorder() {
		return trsBorder;
	}

	public void setTrsBorder(BorderPane trsBorder) {
		this.trsBorder = trsBorder;
	}

	public BorderPane getVideoBorder() {
		return videoBorder;
	}

	public void setVideoBorder(BorderPane videoBorder) {
		this.videoBorder = videoBorder;
	}

	public Stage getResultStage() {
		return resultStage;
	}

	public void setResultStage(Stage resultStage) {
		this.resultStage = resultStage;
	}

	public String getTrsFrom() {
		return trsFrom;
	}

	public void setTrsFrom(String trsFrom) {
		this.trsFrom = trsFrom;
	}

	public String getTrsTo() {
		return trsTo;
	}

	public void setTrsTo(String trsTo) {
		this.trsTo = trsTo;
	}

	public boolean isOcrToggleFlag() {
		return ocrToggleFlag;
	}

	public void setOcrToggleFlag(boolean ocrToggleFlag) {
		this.ocrToggleFlag = ocrToggleFlag;
	}

	public Pane getOcrPane() {
		return ocrPane;
	}

	public void setOcrPane(Pane ocrPane) {
		this.ocrPane = ocrPane;
	}

	public JFXToggleButton getOcrToggleButton() {
		return ocrToggleButton;
	}

	public void setOcrToggleButton(JFXToggleButton ocrToggleButton) {
		this.ocrToggleButton = ocrToggleButton;
	}

}
