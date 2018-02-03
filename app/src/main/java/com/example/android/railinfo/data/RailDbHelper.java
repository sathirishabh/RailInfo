package com.example.android.railinfo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.railinfo.data.RailContract.RailEntry;




public class RailDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ninthtest.db";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + RailEntry.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES2 = "DROP TABLE IF EXISTS " + RailEntry.TABLE_NAME2;


    public RailDbHelper(Context context)

    {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_ENTRIES_QUERY = " CREATE TABLE " + RailEntry.TABLE_NAME + "(" + RailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + RailEntry.COLUMN_NAME1 + " TEXT, " + RailEntry.COLUMN_NAME2 + " TEXT, " + RailEntry.COLUMN_NAME3 + " TEXT, " + RailEntry.COLUMN_NAME4 + " TEXT, " + RailEntry.COLUMN_NAME5 + " TEXT, " + RailEntry.COLUMN_NAME6 + " TEXT, " + RailEntry.COLUMN_FROM + " INTEGER, " + RailEntry.COLUMN_TO + " INTEGER, " + RailEntry.COLUMN_PNR + " TEXT, "+ RailEntry.COLUMN_TRAINNAME + " TEXT, "+ RailEntry.COLUMN_CLASS + " TEXT, "+ RailEntry.COLUMN_BIRTH + " TEXT, " + RailEntry.COLUMN_AGE1 + " INTEGER, " + RailEntry.COLUMN_AGE2 + " INTEGER, " + RailEntry.COLUMN_AGE3 + " INTEGER, " + RailEntry.COLUMN_AGE4 + " INTEGER, " + RailEntry.COLUMN_AGE5 + " INTEGER, " + RailEntry.COLUMN_AGE6 + " INTEGER, " + RailEntry.COLUMN_GENDER1 + " INTEGER, " + RailEntry.COLUMN_GENDER2 + " INTEGER, "+ RailEntry.COLUMN_GENDER3 + " INTEGER, " + RailEntry.COLUMN_GENDER4 + " INTEGER, " + RailEntry.COLUMN_GENDER5 + " INTEGER, " + RailEntry.COLUMN_GENDER6 + " INTEGER, " + RailEntry.COLUMN_DATE + " INTEGER );";
        String SQL_ENTRIES_QUERY2=" CREATE TABLE " + RailEntry.TABLE_NAME2 + "(" + RailEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + RailEntry.COLUMN_FROM + " INTEGER, " + RailEntry.COLUMN_TO + " INTEGER, " + RailEntry.COLUMN_TRAINNAME + " TEXT );";
        db.execSQL(SQL_ENTRIES_QUERY);
        db.execSQL(SQL_ENTRIES_QUERY2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
