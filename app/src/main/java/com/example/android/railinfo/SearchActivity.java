package com.example.android.railinfo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.railinfo.data.RailContract;
import com.example.android.railinfo.data.RailDbHelper;

import java.util.ArrayList;

/**
 * Created by sathirishabh on 06-08-2017.
 */

public class SearchActivity extends AppCompatActivity {


    Spinner tospinner;
    Spinner fromspinner;
    private int mFrom;
    private int mTO;
    ArrayList<String> itemIds;
    private RailDbHelper railDbHelper;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        railDbHelper = new RailDbHelper(this);
        fromspinner=(Spinner)findViewById(R.id.spinnerfrom);
        tospinner=(Spinner)findViewById(R.id.spinnerto);
        setupSpinnerto();
        setupSpinnerfrom();



        Button searchbtn=(Button)findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData();
                intent=new Intent(SearchActivity.this,EditorActivity.class);
                intent.putStringArrayListExtra("List",itemIds);
                intent.putExtra("From",mFrom);
                intent.putExtra("To",mTO);
                startActivity(intent);
            }
        });


    }
    private void setupSpinnerfrom() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_frm_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        fromspinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        fromspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.from_ald))) {
                        mFrom = RailContract.RailEntry.COLUMN_FROM_ALD;
                    } else if (selection.equals(getString(R.string.from_fd))) {
                        mFrom = RailContract.RailEntry.COLUMN_FROM_FD;
                    }
                    else if (selection.equals(getString(R.string.from_knp))) {
                        mFrom = RailContract.RailEntry.COLUMN_FROM_KANPUR;
                    }else {
                        mFrom = RailContract.RailEntry.UNKNOWN;
                    }
                }
            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mFrom = 0; // Unknown
            }
        });
    }
    private void setupSpinnerto() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_to_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        tospinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        tospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.to_ald))) {
                        mTO = RailContract.RailEntry.COLUMN_TO_ALD; // Male
                    } else if (selection.equals(getString(R.string.to_fd))) {
                        mTO = RailContract.RailEntry.COLUMN_TO_FD; // Female
                    }
                    else if (selection.equals(getString(R.string.to_knp))) {
                        mTO = RailContract.RailEntry.COLUMN_TO_KANPUR;
                    }else {
                        mTO= RailContract.RailEntry.UNKNOWN; // Unknown
                    }
                }
            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                mTO = 0;
                // Unknown
            }
        });
    }

    private void displayData() {



        SQLiteDatabase db = railDbHelper.getReadableDatabase();
        String[] projection = {RailContract.RailEntry._ID, RailContract.RailEntry.COLUMN_FROM, RailContract.RailEntry.COLUMN_TO, RailContract.RailEntry.COLUMN_TRAINNAME};
        String selection = RailContract.RailEntry.COLUMN_FROM + "=?" + " AND " + RailContract.RailEntry.COLUMN_TO + "=?";
        String[] selectionArgs = {String.valueOf(mFrom), String.valueOf(mTO)};
        Cursor cursor = db.query(
                RailContract.RailEntry.TABLE_NAME2,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                // The sort order
        );
        try {

            int namecoulmnindex = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_TRAINNAME);
            itemIds = new ArrayList<String>();
            while (cursor.moveToNext()) {


                String name = cursor.getString(namecoulmnindex);

                itemIds.add(name);


            }

        } finally {

            cursor.close();

        }
    }
}
