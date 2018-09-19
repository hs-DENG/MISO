package miso.resource;

public interface IAPIKeys {
   // 네이버 API Key 와 기본 URL
   public static final String NaverClientId = "_rkBX4cdkz7LEldTY9yf";// 애플리케이션 클라이언트 아이디값";
   public static final String NaverClientSecret = "EQ87S9OO3P";// 애플리케이션 클라이언트 시크릿값";
   
   // 플리커 API Key 와 기본 URL
   public static final String FlickrAPIKey = "50293f30edf07ebd7ce8c5eb2d6505e5";
   public static final String FlickrSearchURL = "http://api.flickr.com/services/rest/?method=flickr.photos.search&";
   
   // 다음 API Key 와 기본 URL
   public static final String DaumAPIKey = "ad77311fb8ad425db9e5d4e0a808bc35fa5aacd9";
   public static final String DaumSearchURL = "http://apis.daum.net/search/";
   
   // 유투브 검색 기본 URL
   public static final String YoutubeSearchURL = "http://gdata.youtube.com/feeds/api/videos";
}