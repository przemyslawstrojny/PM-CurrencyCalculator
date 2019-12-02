package com.example.currencycalculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CurrencyAlertsDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CurrencyAlerts.db";

    // SQL Table
    public final class TableStructure
    {
        public static final String TABLE_NAME = "currency_alerts";
        public static final String COLUMN_NAME_CURRENCY = "currency";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_PREV_VALUE = "prev_value";

        public static final String CREATE_TABLE =  "CREATE TABLE "+TABLE_NAME+" ( " +
                COLUMN_NAME_CURRENCY+" varchar(8) NOT NULL PRIMARY KEY," +
                COLUMN_NAME_AMOUNT +" int(11) NOT NULL," +
                COLUMN_NAME_PREV_VALUE + " double NOT NULL" +
                ")";

        public static final String DROP_TABLE =  "DROP TABLE IF EXISTS "+TABLE_NAME;
    }

    public CurrencyAlertsDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TableStructure.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(TableStructure.DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}