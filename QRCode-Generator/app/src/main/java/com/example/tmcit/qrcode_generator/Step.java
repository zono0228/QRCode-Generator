package com.example.tmcit.qrcode_generator;


import java.io.Serializable;

/**
 * 経路の最小単位をもつクラス
 * Created by haseyuuki on 2016/09/07.
 */
public class Step implements Serializable{
    //経路コスト
    public int cost;
    //経路の始点
    public PlaceMark start;
    //経路の終点
    public PlaceMark end;

}
