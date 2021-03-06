package com.example.currencycalculator;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

    public void getCurrency(String currencyCode, Callback callback)
    {
        final String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currencyCode;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }
}
