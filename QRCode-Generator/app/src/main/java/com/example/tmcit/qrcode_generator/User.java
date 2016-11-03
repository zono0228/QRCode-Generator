package com.example.tmcit.qrcode_generator;


import java.io.Serializable;
import java.util.Date;

/**
 * ユーザ情報のクラス
 * Created by haseyuuki on 2016/09/03.
 */
public class User implements Serializable{
    ///ユーザID
    public int id;
    /// 生まれ年
    public int bornIn;
    /// 性別
    public Sexes sex;
    /// 出身国
    public Countries country;
    /// 登録日時
    public Date createdAt;

}
