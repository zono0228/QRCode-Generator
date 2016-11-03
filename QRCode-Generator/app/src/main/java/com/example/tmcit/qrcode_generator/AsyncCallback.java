package com.example.tmcit.qrcode_generator;

public interface AsyncCallback {
    void onComplete(String result); //非同期処理終了時にonPostExecuteで呼び出す
}
