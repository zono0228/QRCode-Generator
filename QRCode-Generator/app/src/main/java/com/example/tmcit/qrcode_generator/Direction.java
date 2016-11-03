package com.example.tmcit.qrcode_generator;

import java.io.Serializable;
import java.util.List;

/**
 * 経路案内候補を表します
 * Created by haseyuuki on 2016/09/07.
 */
public class Direction implements Serializable{
    //経路取得要求が記録されたかどうかを示す値
    public boolean haseRegistred;
    //経路の始点
    public PlaceMark origin;
    //経路の終点
    public PlaceMark destination;
    //経路の候補
    public List<Route> routes;

}
