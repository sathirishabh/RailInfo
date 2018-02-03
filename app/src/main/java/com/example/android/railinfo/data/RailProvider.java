package com.example.android.railinfo.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.util.Log;
import android.widget.Switch;

import com.example.android.railinfo.data.RailContract.RailEntry;

import static android.R.attr.id;

/**
 * Created by sathirishabh on 20-07-2017.
 */

public class RailProvider extends ContentProvider {
    public static final String LOG_TAG=RailProvider.class.getSimpleName();
    private static final int RAILS=1;
    private static final int RAIL_ID=2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {

        sUriMatcher.addURI("com.example.android.railinfo", "rails",1);


        sUriMatcher.addURI("com.example.android.railinfo", "rails/#",2);

    }
    private RailDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new RailDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortorder) {
        SQLiteDatabase database=mDbHelper.getReadableDatabase();
        Cursor cursor=null;

       int match=sUriMatcher.match(uri);

        switch (match)
        {
            case RAILS:
                cursor=database.query(RailEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortorder);
                break;

            case RAIL_ID:
                selection=RailEntry._ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=database.query(RailEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortorder);
                 break;
             default:
                 throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {


        final int match=sUriMatcher.match(uri);
        switch(match)
        {
            case RAILS:
                return insertValue(uri,values);
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }

    }


    private Uri insertValue(Uri uri, ContentValues values){
        SQLiteDatabase database=mDbHelper.getWritableDatabase();

        long id=database.insert(RailEntry.TABLE_NAME,null,values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RAILS:
                // Delete all rows that match the selection and selection args
                return database.delete(RailEntry.TABLE_NAME, selection, selectionArgs);
            case RAIL_ID:
                // Delete a single row given by the ID in the URI
                selection = RailEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int deleted = database.delete(RailEntry.TABLE_NAME, selection, selectionArgs);
                if (deleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return deleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RAILS:
                return updateValue(uri, contentValues, selection, selectionArgs);
            case RAIL_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = RailEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                return updateValue(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);

        }

    }

    private int updateValue(Uri uri, ContentValues values, String selection, String[] selectionArgs) {



        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Returns the number of database rows affected by the update statement

        int rowsUpdated = database.update(RailEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }
}
