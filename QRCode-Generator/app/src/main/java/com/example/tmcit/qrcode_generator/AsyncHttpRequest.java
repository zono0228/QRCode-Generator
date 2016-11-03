package com.example.tmcit.qrcode_generator;

import android.os.AsyncTask;

public abstract class AsyncHttpRequest extends AsyncTask<String, Void, String> {
    protected AsyncCallback callback = null;
}
