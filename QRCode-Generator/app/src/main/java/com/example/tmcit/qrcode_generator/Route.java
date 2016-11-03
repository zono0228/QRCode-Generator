package com.example.tmcit.qrcode_generator;


import java.io.Serializable;
import java.util.List;

/**
 * 案内経路情報のクラス
 * Created by haseyuuki on 2016/09/07.
 */
public class Route implements Serializable {
    //経路全体のコスト
    public int totalCost;
    //段階的な経路
    public List<Step> steps;
}
