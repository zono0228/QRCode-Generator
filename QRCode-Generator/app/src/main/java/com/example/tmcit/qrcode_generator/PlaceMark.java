package com.example.tmcit.qrcode_generator;


import java.io.Serializable;

/**
 *  地図上に位置する経路案内可能な地点を表します。
 * Created by haseyuuki on 2016/09/03.
 */
public class PlaceMark implements Serializable{

                 /// この PlaceMark の場所ID
                 public int id;
                 /// この PlaceMark の位置するX座標
                 public int x;
                 /// この PlaceMark の位置するY座標
                 public int y;
                 /// この PlaceMark の位置する階数
                 public int floor;
                 /// この PlaceMark の種類
                 public PlaceMarkType type;
                 /// Type が Place または Warp の場合にのみ格納される場所名
                 public String name;
                 /// Type が Light の場合にのみ格納される照明ID
                 public int lightId;
                 /// Type が Warp の場合にのみ格納される接続ID
                 public int warpId;
    }
