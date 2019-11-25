package com.example.currencycalculator;


import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebClient
{
    private static WebClient instance = null;
    private OkHttpClient client = new OkHttpClient();

    private WebClient()
    {

    }

    public static WebClient getInstance()
    {
        if (instance == null)
            instance = new WebClient();

        return instance;
    }

    public void getCurrencies(Callback callback)
    {
        final String url = "http://api.nbp.pl/api/exchangerates/tables/A";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }
}
