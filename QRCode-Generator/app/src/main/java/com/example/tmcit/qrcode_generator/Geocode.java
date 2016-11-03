package com.example.tmcit.qrcode_generator;


import java.io.Serializable;

/**
 * Created by haseyuuki on 2016/09/08.
 */
public class Geocode implements Serializable {
    //x座標位置
    public int x;
    //y座標位置
    public int y;
    //階
    public int floor;
    //方向角度（-180～180）
    public int angle;
    //天井の照明
    public PlaceMark ceilingLight;
    //床に反射した照明
    public PlaceMark floorLight;
}
