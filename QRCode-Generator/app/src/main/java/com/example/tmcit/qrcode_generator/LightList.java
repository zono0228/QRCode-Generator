package com.example.tmcit.qrcode_generator;

import java.io.Serializable;
import java.util.List;

/**
 * 照明情報リストクラス
 * Created by haseyuuki on 2016/09/03.
 */
public class LightList implements Serializable {
    //照明情報の配列
    public List<Light> lights;
}
