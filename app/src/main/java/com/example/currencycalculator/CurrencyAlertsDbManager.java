package com.example.currencycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CurrencyAlertsDbManager
{
    private static CurrencyAlertsDbManager instance = null;

    private CurrencyAlertsDbManager() { }

    public static CurrencyAlertsDbManager getInstance()
    {
        if (instance == null)
            instance = new CurrencyAlertsDbManager();

        return instance;
    }

    public final class CurrencyAlert
    {
        public String Name;
        public int Amount;
        public double PrevValue;

        public CurrencyAlert() {}
        public CurrencyAlert(String Name, int Amount, double PrevValue)
        {
            this.Name = Name;
            this.Amount = Amount;
            this.PrevValue = PrevValue;
        }
    }

    public final void SetCurrencyAlert(Context context, String currency, int amount, double prev_value)
    {
        CurrencyAlertsDbHelper dbHelper = new CurrencyAlertsDbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY, currency);
        values.put(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_AMOUNT, amount);
        values.put(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_PREV_VALUE, prev_value);

        db.insertWithOnConflict(CurrencyAlertsDbHelper.TableStructure.TABLE_NAME, null, values, db.CONFLICT_REPLACE);
    }

    public final CurrencyAlert GetCurrencyAlert(Context context, String currency)
    {
        CurrencyAlertsDbHelper dbHelper = new CurrencyAlertsDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY,
                CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_AMOUNT,
                CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_PREV_VALUE
            };

        String selection = CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY + " = ?";
        String[] selectionArgs = { currency };

        Cursor cursor = db.query(
                CurrencyAlertsDbHelper.TableStructure.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            CurrencyAlert data = new CurrencyAlert();

            data.Name = cursor.getString(cursor.getColumnIndex(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY));
            data.Amount = cursor.getInt(cursor.getColumnIndex(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_AMOUNT));
            data.PrevValue = cursor.getDouble(cursor.getColumnIndex(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_PREV_VALUE));

            return data;
        }

        return null;
    }

    public final int RemoveCurrencyAlert(Context context, String currency)
    {
        CurrencyAlertsDbHelper dbHelper = new CurrencyAlertsDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY + " LIKE ?";
        String[] selectionArgs = { currency };

        int result = db.delete(CurrencyAlertsDbHelper.TableStructure.TABLE_NAME, selection, selectionArgs);

        return result;
    }

    public final ArrayList<CurrencyAlert> GetAllCurrencyAlerts(Context context)
    {
        CurrencyAlertsDbHelper dbHelper = new CurrencyAlertsDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY,
                CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_AMOUNT,
                CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_PREV_VALUE

        };

        Cursor cursor = db.query(
                CurrencyAlertsDbHelper.TableStructure.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<CurrencyAlert> array = new ArrayList<CurrencyAlert>();

        if (cursor != null && cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                String CurrCurrency = cursor.getString(cursor.getColumnIndex(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_CURRENCY));
                int CurrAmount = cursor.getInt(cursor.getColumnIndex(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_AMOUNT));
                double CurrPrevValue = cursor.getDouble(cursor.getColumnIndex(CurrencyAlertsDbHelper.TableStructure.COLUMN_NAME_PREV_VALUE));

                array.add(new CurrencyAlert(CurrCurrency, CurrAmount, CurrPrevValue));
            }

            return array;
        }

        return null;
    }
}
