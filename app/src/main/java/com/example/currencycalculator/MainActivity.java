package com.example.currencycalculator;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static BroadcastReceiver mNetworkReceiver;
    static TextView connection_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection_field=(TextView) findViewById(R.id.connection_field);
        mNetworkReceiver = new ReceiverNetwork();
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentCurrencyCalculator()).commit();


        // Run/Refresh CurrencyAlertsService
        Intent serviceIntent = new Intent(this, CurrencyAlertsService.class);
        startService(serviceIntent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_search:
                            selectedFragment = new FragmentCurrencyCalculator();
                            break;
                        case R.id.nav_show_chart:
                            selectedFragment = new FragmentAnalytics();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public static void dialog(boolean value){

        if(value){
            connection_field.setVisibility(View.GONE);
        }else {
            connection_field.setVisibility(View.VISIBLE);
            connection_field.setText("No Internet connection");
            connection_field.setBackgroundColor(Color.RED);
            connection_field.setTextColor(Color.WHITE);
        }
    }
}
