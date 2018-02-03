package com.example.android.railinfo;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.railinfo.data.RailContract.RailEntry;

/**
 * Created by sathirishabh on 19-07-2017.
 */

public class RailCursorAdapter extends CursorAdapter {
    public RailCursorAdapter(Context context, Cursor c) {

        super(context, c, 0 /* flags */);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.textView_name);
        TextView tvPriority = (TextView) view.findViewById(R.id.textView_from);
        int nameColumnIndex = cursor.getColumnIndex(RailEntry.COLUMN_NAME1);
        int dateColumnIndex = cursor.getColumnIndex(RailEntry.COLUMN_DATE);

                       // Read the pet attributes from the Cursor for the current pet
              String petName = cursor.getString(nameColumnIndex);
               int fromcolumn = cursor.getInt(dateColumnIndex);



        tvBody.setText(petName);
        tvPriority.setText(String.valueOf(fromcolumn));

    }
}
