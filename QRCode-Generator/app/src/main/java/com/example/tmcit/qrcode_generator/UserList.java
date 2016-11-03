package com.example.tmcit.qrcode_generator;


import java.io.Serializable;
import java.util.List;

/**
 * ユーザ情報リストのクラス
 * Created by haseyuuki on 2016/09/03.
 */
public class UserList implements Serializable{
    /// ユーザ情報の配列
    public List<User> users;
}
