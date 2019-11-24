package com.example.currencycalculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Currency> parseCurrencyResponse(String responseString){
        ArrayList<Currency> currencyArrayList = new ArrayList<>();
        try {
            final JSONObject jsonObject = new JSONObject(responseString);
            final JSONArray currencies = jsonObject.getJSONArray("rates");
            for (int i =0; i < currencies.length(); i++){
                final JSONObject currency = currencies.getJSONObject(i);
                currencyArrayList.add(new Currency(currency.getString("currency"),
                        currency.getString("code"), currency.getDouble("mid")));
            }
            return currencyArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
