package com.example.currencycalculator;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CurrencyAlertsService extends Service
{
    public CurrencyAlertsService() { }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create Timer
        timer = new Timer();
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // Clear Timer
        clearTimerSchedule();

        // Init Timer
        initTask();
        timer.scheduleAtFixedRate(timerTask, 0, 60 * 1000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        // Clear Timer
        clearTimerSchedule();

        super.onDestroy();
    }

    // Context
    private Context context = this;

    // Timer
    private Toast toast;
    private Timer timer;
    private TimerTask timerTask;

    private class CheckCurrencyAlerts extends TimerTask
    {
        @Override
        public void run()
        {
            WebClient.getInstance().getCurrencies(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        // Parse result
                        Parser currencyParser = new Parser();

                        // Get list of currencies
                        List<Currency> CurrenciesRates = currencyParser.parseCurrencyResponse(response);

                        // Get active alerts
                        ArrayList<CurrencyAlertsDbManager.CurrencyAlert> AlertList = CurrencyAlertsDbManager.getInstance().GetAllCurrencyAlerts(context);

                        // Is any alert
                        if (AlertList != null)
                        {
                            for (CurrencyAlertsDbManager.CurrencyAlert CurrAlert : AlertList)
                            {
                                for (Currency CurrCurrency : CurrenciesRates)
                                {
                                    if (CurrCurrency.getCurrency_code().equals(CurrAlert.Name))
                                    {
                                        Log.d("CurrencyAlertsService",
                                                "Currency code: " + CurrAlert.Name +
                                                        ", current rate: " + CurrCurrency.getAverage_rate() +
                                                        ", notify when rate under: " + (CurrAlert.PrevValue - CurrAlert.PrevValue * (CurrAlert.Amount/100.0)));

                                        if ((CurrAlert.PrevValue - CurrAlert.PrevValue * (CurrAlert.Amount/100.0)) >= CurrCurrency.getAverage_rate())
                                        {
                                            // Change to notification in future
                                            showToast("Rate of " + CurrAlert.Name + " fell down by " + CurrAlert.Amount +"%");

                                            // Remove alert
                                            CurrencyAlertsDbManager.getInstance().RemoveCurrencyAlert(context, CurrAlert.Name);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private void clearTimerSchedule()
    {
        if(timerTask != null)
        {
            timerTask.cancel();
            timer.purge();
        }
    }

    private void initTask()
    {
        timerTask = new CheckCurrencyAlerts();
    }

    private void showToast(String text)
    {
        toast.setText(text);
        toast.show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
