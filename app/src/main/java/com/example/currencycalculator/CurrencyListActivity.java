package com.example.currencycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CurrencyListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Currency> adapter;
    private List<Currency> currencyList = new ArrayList<>();
    private  WebClient webClient = WebClient.getInstance();
    private Parser parser = new Parser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        listView = findViewById(R.id.listView1);

        webClient.getCurrencies(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final List<Currency> currencies = parser.parseCurrencyResponse(response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<Currency>(CurrencyListActivity.this, android.R.layout.simple_list_item_1, currencies);
                        listView.setAdapter(adapter);
                        onListViewClickListener();
                    }
                });
            }
        });
    }

    private void onListViewClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Currency itemStr = adapter.getItem(i);

                Intent intent = new Intent();
                intent.putExtra("message", itemStr.getCurrency_name().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

}
