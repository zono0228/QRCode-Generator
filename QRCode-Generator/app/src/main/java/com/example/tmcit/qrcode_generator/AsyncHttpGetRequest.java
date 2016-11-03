package com.example.tmcit.qrcode_generator;

public class AsyncHttpGetRequest extends AsyncHttpRequest {

    public AsyncHttpGetRequest(AsyncCallback callback) {
        super.callback = callback;
    }

    /***
     * POST送信を非同期で処理します。
     * @param params [0]: 送信先URL
     *               [1]: 送信するJSONデータ
     * @return POST送信のレスポンス
     */

    @Override
    protected String doInBackground(String... params) {
        String address = params[0]+params[1];
//        String json = params[1];
        return HttpJson.Get(address);
    }

    // 処理が終了したら結果をcallbackに投げる
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (callback != null) {
            callback.onComplete(result);
        }
    }
}
