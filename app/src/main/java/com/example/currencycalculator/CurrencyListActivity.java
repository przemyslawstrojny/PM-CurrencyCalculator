package com.example.currencycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CurrencyListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Currency> adapter;
    private List<Currency> currencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        listView = findViewById(R.id.listView1);
        testowygeneratordousuniecia();

        adapter = new ArrayAdapter<Currency>(this, android.R.layout.simple_list_item_1, currencyList);
        listView.setAdapter(adapter);
        onListViewClickListener();
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

    private void testowygeneratordousuniecia(){
        currencyList.add(new Currency("PLN", 1,"1",1));
        currencyList.add(new Currency("EUR", 1,"1",1));
        currencyList.add(new Currency("USD", 1,"1",1));
    }
}
