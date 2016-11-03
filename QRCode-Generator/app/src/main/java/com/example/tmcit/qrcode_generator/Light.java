package com.example.tmcit.qrcode_generator;

import java.io.Serializable;

/**
 * 照明情報クラス
 * Created by haseyuuki on 2016/09/03.
 */
public class Light implements Serializable{
   public int id;
    /// X座標地点
    public int x;
    /// Y座標地点
    public int y;
    /// 階
    public int floor;
    //PlaceMarkの種類
    public PlaceMarkType type;
    //Type が Place または Warp の場合にのみ格納される場所名
    public String name;
    /// 照明ID
    public int lightId;
    //ワープID
    public int warpId;


}
