package com.example.android.railinfo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.railinfo.data.RailContract;
import com.example.android.railinfo.data.RailDbHelper;


/**
 * Created by sathirishabh on 07-08-2017.
 */

public class AdminActivity extends AppCompatActivity {
    private RailDbHelper mDbHelper;
    private int mFrom;
    private int mTO;
    Spinner fromspinner;

    Spinner tospinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mDbHelper = new RailDbHelper(this);
        fromspinner=(Spinner)findViewById(R.id.spinneradminfrom);
        tospinner=(Spinner)findViewById(R.id.spinneradminto);


        setupSpinnerfrom();
        setupSpinnerto();
        Button bt=(Button)findViewById(R.id.buttonadmin);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

    }
    private void insertData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        EditText ed=(EditText)findViewById(R.id.trainnameadmin);
        String Trainname=ed.getText().toString();

        ContentValues values = new ContentValues();


        values.put(RailContract.RailEntry.COLUMN_FROM, mFrom);
        values.put(RailContract.RailEntry.COLUMN_TO, mTO);
        values.put(RailContract.RailEntry.COLUMN_TRAINNAME, Trainname);
        long newrowid=db.insert(RailContract.RailEntry.TABLE_NAME2,null,values);
        if(newrowid !=0){
            Toast.makeText(this, getString(R.string.sucess_full),
                    Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this, getString(R.string.error_failed),
                    Toast.LENGTH_SHORT).show();
        }



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

}
