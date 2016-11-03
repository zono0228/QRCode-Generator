package com.example.tmcit.qrcode_generator;

/**
 * 富士通知財のAPIを扱うクラス
 * Created by haseyuuki on 2016/09/13.
 */
public class FujitsuChizaiAPI {

    /*Geocode API*/
    public static class Geocode{
        private final static String endpoint = "https://fujitsu-chizai.azurewebsites.net/api/geocode";
        //指定した照明ID間の座標を取得するメソッド
        public static void getGeocode(int cellingLight,int floorLight, AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint, "?ceilingLightId="+cellingLight+"&floorLightId="+floorLight);
        }
    }


    /*Direction API*/
    public static class Direction{
        private final static String endpoint = "https://fujitsu-chizai.azurewebsites.net/api/directions";
        //2点間の経路案内情報を取得
        public static void getDirection(int originId,int destinationId,PlaceMarkType originType,PlaceMarkType destinationType,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?originId="+originId+"&destinationId="+destinationId+"&originType="+originType+"&destinationType="+destinationType );
        }
        //2点間の経路案内情報の取得と指定したUserIdの経路検索情報としてDBに保存するメソッド
        public static void getDirectionRegistered(int userId,int originId,int destinationId,PlaceMarkType originType,PlaceMarkType destinationType,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?originId="+String.valueOf(originId)+"&destinationId="+String.valueOf(destinationId)+"&userId="+String.valueOf(userId)+"&originType="+originType+"&destinationType="+destinationType);
        }

    }


    /*User API*/
    public static class Users {
        private static final String endpoint = "http://fujitsu-chizai.azurewebsites.net/api/users/";
        //指定されたIDからユーザ情報を取得するメソッド
        public static void at(int id,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,String.valueOf(id));
        }
        //すべてのユーザのユーザ情報をリストで取得するメソッド
        public static void all(AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"");
        }
        //ユーザの登録を行うメソッド
        public static void register(User user, AsyncCallback callback) {
            new AsyncHttpPostRequest(callback).execute(endpoint, ParseJson.toJson(user));
        }
    }


    /*Place API*/
    public static class Place{
        private static String endpoint ="http://fujitsu-chizai.azurewebsites.net/api/places";
        //指定されたIDから場所情報を取得するメソッド
        public static void at(Integer id, AsyncCallback callback) {
            new AsyncHttpGetRequest(callback).execute(endpoint,"/"+String.valueOf(id));
        }
        //すべての照明光IDの照明情報をリストを取得するメソッド
        public static void all( AsyncCallback callback) {
            new AsyncHttpGetRequest(callback).execute(endpoint,"");
        }
        //キーワードで曖昧検索した場所情報を取得するメソッド
        public static void search(String keyword,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?keyword="+keyword);
        }
        //指定した座標(x,y)、階(floor)の半径radiusの場所情報リストを取得するメソッド
        public static void search(int x,int y,int floor,int radius,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?x=" + String.valueOf(x) + "&y=" + String.valueOf(y) + "&floor=" + String.valueOf(floor) + "&radius=" + String.valueOf(radius));
        }
        //指定した座標(x,y)、階(floor)の半径radiusの場所情報リストからキーワードで曖昧検索した結果のアンドを取得するメソッド
        public static void search(String keyword,int x,int y,int floor,int radius,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?keyword="+ keyword +"&x=" + String.valueOf(x) + "&y=" + String.valueOf(y) + "&floor=" + String.valueOf(floor) + "&radius=" + String.valueOf(radius));
        }
        //指定した階数のすべての場所情報を取得
        public static void search(int floor,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?floor=" + String.valueOf(floor));
        }

    }


    /*Light API*/
    public static class Light{
        private static String endpoint ="http://fujitsu-chizai.azurewebsites.net/api/lights";
        //指定された照明光IDに一致する照明情報を取得するメソッド
        public static void at(Integer id, AsyncCallback callback) {
            new AsyncHttpGetRequest(callback).execute(endpoint,"/"+String.valueOf(id));
        }
        //すべての照明光IDの照明情報をリストを取得するメソッド
        public static void all(AsyncCallback callback) {
            new AsyncHttpGetRequest(callback).execute(endpoint,"");
        }
        //指定した座標(x,y)、階(floor)の半径radiusの照明情報リストを取得するメソッド
        public static void search(int x,int y,int floor,int radius,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?x=" + String.valueOf(x) + "&y=" + String.valueOf(y) + "&floor=" + String.valueOf(floor) + "&radius=" + String.valueOf(radius));
        }
        //指定した階数のすべての照明情報を取得するメソッド
        public static void search(int floor,AsyncCallback callback){
            new AsyncHttpGetRequest(callback).execute(endpoint,"?floor=" + String.valueOf(floor));
        }

    }

}
