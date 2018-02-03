package com.example.android.railinfo;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.railinfo.EditorActivity;

import com.example.android.railinfo.GenerateOtp;
import com.example.android.railinfo.data.RailContract;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by sathirishabh on 28-07-2017.
 */

public class OTPActivity extends AppCompatActivity {
    char[] notp;
     EditText otp;
    EditText mob;
        EditorActivity ed;
    ContentValues values;
    ContentValues[] mValueArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

          mob=(EditText)findViewById(R.id.textview_mobile);
        otp=(EditText)findViewById(R.id.textview_otp);
        final Button genotp=(Button)findViewById(R.id.btn_genotp);
        final Button verfyotp=(Button)findViewById(R.id.btn_genverify);

        int from=getIntent().getExtras().getInt("From");
        int to=getIntent().getExtras().getInt("To");
        String pnr=getIntent().getExtras().getString("Pnr");
        int date=getIntent().getExtras().getInt("Date");
        String Birth=getIntent().getExtras().getString("Birth");
        String Class=getIntent().getExtras().getString("Class");
        String TrainName=getIntent().getExtras().getString("TrainName");

        String name1=getIntent().getExtras().getString("Name1");
        int age1=getIntent().getExtras().getInt("Age1");
        int gen1=getIntent().getExtras().getInt("Gender1");

        String name2=getIntent().getExtras().getString("Name2");
        int age2=getIntent().getExtras().getInt("Age2");
        int gen2=getIntent().getExtras().getInt("Gender2");

        String name3=getIntent().getExtras().getString("Name3");
        int age3=getIntent().getExtras().getInt("Age3");
        int gen3=getIntent().getExtras().getInt("Gender3");

        String name4=getIntent().getExtras().getString("Name4");
        int age4=getIntent().getExtras().getInt("Age4");
        int gen4=getIntent().getExtras().getInt("Gender4");

        String name5=getIntent().getExtras().getString("Name5");
        int age5=getIntent().getExtras().getInt("Age5");
        int gen5=getIntent().getExtras().getInt("Gender5");

        String name6=getIntent().getExtras().getString("Name6");
        int age6=getIntent().getExtras().getInt("Age6");
        int gen6=getIntent().getExtras().getInt("Gender6");



        values = new ContentValues();


        values.put(RailContract.RailEntry.COLUMN_FROM, from);
        values.put(RailContract.RailEntry.COLUMN_TO, to);
        values.put(RailContract.RailEntry.COLUMN_PNR,pnr);
        values.put(RailContract.RailEntry.COLUMN_DATE, date);




        values.put(RailContract.RailEntry.COLUMN_NAME1, name1);
       values.put(RailContract.RailEntry.COLUMN_AGE1,age1);
        values.put(RailContract.RailEntry.COLUMN_GENDER1,gen1);



       values.put(RailContract.RailEntry.COLUMN_NAME2, name2);
        values.put(RailContract.RailEntry.COLUMN_AGE2,age2);
        values.put(RailContract.RailEntry.COLUMN_GENDER2,gen2);
        values.put(RailContract.RailEntry.COLUMN_TRAINNAME,TrainName);
        values.put(RailContract.RailEntry.COLUMN_BIRTH,Birth);
        values.put(RailContract.RailEntry.COLUMN_CLASS,Class);


       values.put(RailContract.RailEntry.COLUMN_NAME3, name3);
        values.put(RailContract.RailEntry.COLUMN_AGE3,age3);
       values.put(RailContract.RailEntry.COLUMN_GENDER3,gen3);
        values.put(RailContract.RailEntry.COLUMN_NAME4, name4);
        values.put(RailContract.RailEntry.COLUMN_AGE4,age4);
        values.put(RailContract.RailEntry.COLUMN_GENDER4,gen4);
        values.put(RailContract.RailEntry.COLUMN_NAME5, name5);
        values.put(RailContract.RailEntry.COLUMN_AGE5,age5);
        values.put(RailContract.RailEntry.COLUMN_GENDER5,gen5);
        values.put(RailContract.RailEntry.COLUMN_NAME6, name6);
        values.put(RailContract.RailEntry.COLUMN_AGE6,age6);
        values.put(RailContract.RailEntry.COLUMN_GENDER6,gen6);



        genotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               generateotp();
            }
        });
        verfyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyotp();
            }
        });

    }

    public void generateotp(){
        String mobilenumber=mob.getText().toString().trim();
        notp=GenerateOtp.OTP(4);
        if (notp == null) {
            // If the new content URI is null, then there was an error with insertion.


        } else {
            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, String.valueOf(notp),
                    Toast.LENGTH_SHORT).show();
        }
        /*Uri uri = Uri.parse("smsto:"+mobilenumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms _body", String.valueOf(notp));
        startActivity(it);*/
    }


    public void verifyotp()
    {
        String enteredotp=otp.getText().toString().trim();


        Intent intent=new Intent(this,CatalogActivity.class);
        if(enteredotp.equals(String.valueOf(notp)))
        {
            Uri newUri = getContentResolver().insert(RailContract.RailEntry.CONTENT_URI, values);


            // Show a toast message depending on whether or not the insertion was successful
            if(newUri == null ) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.error_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.sucess_full),
                        Toast.LENGTH_SHORT).show();
            }

            startActivity(intent);

        }

    }


}
