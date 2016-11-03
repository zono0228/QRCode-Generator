package com.example.tmcit.qrcode_generator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 * Httpの処理用クラス
 * Created by haseyuuki on 2016/09/06.
 */
public class HttpJson {
    private static String json = null;
    private static String language;// = "ja";


    public static void setLanguage(String lang){
        language = lang;
    }

    //Getメソッド　指定したアドレスにGetメソッドで通信を行う
    public static String Get(String address) {
        try {
            URL url = new URL( address );
            // HttpURLConnection インスタンス生成
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // タイムアウト設定
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);
            // リクエストメソッド
            urlConnection.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            urlConnection.setInstanceFollowRedirects(false);
            // ヘッダーの設定(複数設定可能)

            Locale locale = Locale.getDefault();
            language = locale.getLanguage();

            urlConnection.setRequestProperty("Accept-Language", language);
            // 接続
            urlConnection.connect();
            int resp = urlConnection.getResponseCode();
            switch (resp){
                case HttpURLConnection.HTTP_OK:
                    InputStream is = urlConnection.getInputStream();
                    json = InputStreamToString(is);
                    is.close();
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    json = "Http_Unauthorized";
                    break;
                default:
                    json = "default";
                    break;
            }
            } catch (Exception e) {
              e.printStackTrace();
            }
        return json;
    }

    //取得したストリームを文字列に変換するメソッド
    public static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static String Post(String address, String json) {
        String response = null;
        HttpURLConnection con = null;
        try {
            // 設定
            URL url = new URL(address);
            con = (HttpURLConnection)url.openConnection();
            con.setReadTimeout(10000);
            con.setConnectTimeout(20000);
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("Accept-Language", "jp");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            // 送信
            OutputStream os = con.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print(json);
            ps.close();

            // 受信
            int res = con.getResponseCode();
            switch (res){
                case HttpURLConnection.HTTP_OK:
                    InputStream is = con.getInputStream();
                    response = InputStreamToString(is);
                    is.close();
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    throw new Exception("HTTP_BAD_REQUEST");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return  response;
    }
}

/*

    private String downloadJson(String address) {
        String str = null;

        try {
            URL url = new URL( address );

            // HttpURLConnection インスタンス生成
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // タイムアウト設定
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);

            // リクエストメソッド
            urlConnection.setRequestMethod("GET");

            // リダイレクトを自動で許可しない設定
            urlConnection.setInstanceFollowRedirects(false);

            // ヘッダーの設定(複数設定可能)
            urlConnection.setRequestProperty("Accept-Language", "jp");

            // 接続
            urlConnection.connect();

            int resp = urlConnection.getResponseCode();

            switch (resp){
                case HttpURLConnection.HTTP_OK:
                    InputStream is = urlConnection.getInputStream();
                    str = InputStreamToString(is);
                    is.close();
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.d(TAG, "downloadJson error");
            e.printStackTrace();
        }

        return str;
    }

    //取得したストリームを文字列に変換するメソッド
    static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
*/

