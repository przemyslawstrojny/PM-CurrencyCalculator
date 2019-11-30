package com.example.currencycalculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class Parser {

    public String parseUpdateDate(Response response){
        String date;
        try{
            final JSONArray jsonArray = new JSONArray(response.body().string());
            final JSONObject jsonObject = jsonArray.getJSONObject(0);
            date = jsonObject.getString("effectiveDate");
            return date;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Currency> parseCurrencyResponse(Response responseString){
        ArrayList<Currency> currencyArrayList = new ArrayList<>();
        try {
            final JSONArray jsonArray = new JSONArray(responseString.body().string());
            final JSONObject jsonObject = jsonArray.getJSONObject(0);
            final JSONArray currencies = jsonObject.getJSONArray("rates");
            for (int i =0; i < currencies.length(); i++){
                final JSONObject currency = currencies.getJSONObject(i);
                currencyArrayList.add(new Currency(currency.getString("currency"),
                        currency.getString("code"), currency.getDouble("mid")));
            }
            return currencyArrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
