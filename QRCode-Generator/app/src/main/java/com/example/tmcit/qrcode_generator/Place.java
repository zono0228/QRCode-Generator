package com.example.tmcit.qrcode_generator;

import java.io.Serializable;

/**
 * 場所情報クラス
 * Created by haseyuuki on 2016/09/03.
 */
public class Place implements Serializable{
    /// X座標位置
    public int x;
    /// Y座標位置
    public int y;
    /// 階
    public int floor;
    /// 場所名
    public String name;
    /// この場所の種類（ Place または Warp ）
    public PlaceMarkType type;//PlaceMarkType
    /// 接続ID
    public int warpId;
}

