package com.example.tmcit.qrcode_generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

/**
 * Jsonから各オブジェクトにパースを行うクラスです。
 * Created by haseyuuki on 2016/09/05.
 */
public class ParseJson {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    

    //Jsonから　PlaceMark 単体へパースを行う
    public static PlaceMark parsePlace(String json) throws JSONException{
        return gson.fromJson(json,PlaceMark.class);
    }
    //Jsonから　PlaceMark配列へパースを行う
    public static PlaceList parsePlaceList(String json) throws JSONException {
        return gson.fromJson(json, PlaceList.class);
    }

    //Jsonから　User単体へパースを行う
    public static User parseUser(String json) throws JSONException {
        return gson.fromJson(json, User.class);
    }
    //Jsonから　User配列へパースを行う
    public static UserList parseUserList(String json) throws JSONException {
        return gson.fromJson(json, UserList.class);
    }

    //Jsonから　User単体へパースを行う
    public static Light parseLight(String json) throws JSONException {
        return gson.fromJson(json, Light.class);
    }
    //Jsonから　Light配列へパースを行う
    public static LightList parseLightList(String json) throws JSONException {
        return gson.fromJson(json, LightList.class);
    }

    //Jsonから　Directionへパースを行う
    public static Direction parseDirection(String json) throws JSONException{
        return gson.fromJson(json, Direction.class);
    }

    public static Geocode parseGeocode(String json) throws JSONException{
        return gson.fromJson(json, Geocode.class);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

}