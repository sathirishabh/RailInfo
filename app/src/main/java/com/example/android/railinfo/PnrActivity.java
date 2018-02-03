package com.example.android.railinfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.railinfo.data.RailContract;
import com.example.android.railinfo.data.RailDbHelper;

/**
 * Created by sathirishabh on 21-07-2017.
 */

public class PnrActivity extends AppCompatActivity {
    private RailDbHelper railDbHelper;
    LinearLayout ll;
    private TextView forName;
    private TextView forTo;
    private TextView forFrom;
    private TextView forDate;
    private TextView forPnr;
    private TextView forAge;
    private TextView forOther;
    private TextView forGen;
    String Gen2;
    String Gen3;
    String Gen4;
    String Gen5;
    String Gen6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr);
        railDbHelper = new RailDbHelper(this);
        forName=(TextView)findViewById(R.id.for_name2);

        forFrom=(TextView)findViewById(R.id.for_from2);
        forTo=(TextView)findViewById(R.id.for_to2);
        forDate=(TextView)findViewById(R.id.for_date2);
        forPnr=(TextView)findViewById(R.id.for_pnr2);
        forAge=(TextView)findViewById(R.id.for_age2);
        forOther=(TextView)findViewById(R.id.for_other2);

        forGen=(TextView)findViewById(R.id.for_gen2);
         ll=(LinearLayout)findViewById(R.id.booking);
        ll.setVisibility(View.INVISIBLE);
        final Button bt=(Button)findViewById(R.id.pnr_no);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayData();
            }
        });


    }
    private void displayData() {
        String age2;
        String age3;
        String age4;
        String age5;
        String age6;


        SQLiteDatabase db = railDbHelper.getReadableDatabase();
        EditText pnreditText=(EditText)findViewById(R.id.pnr_value);
        String enterdpnr=pnreditText.getText().toString().trim();
        String[] projection = {RailContract.RailEntry._ID,
                RailContract.RailEntry.COLUMN_NAME1,
                RailContract.RailEntry.COLUMN_NAME2,
                RailContract.RailEntry.COLUMN_NAME3,
                RailContract.RailEntry.COLUMN_NAME4,
                RailContract.RailEntry.COLUMN_NAME5,
                RailContract.RailEntry.COLUMN_NAME6,
                RailContract.RailEntry.COLUMN_FROM,
                RailContract.RailEntry.COLUMN_TO,
                RailContract.RailEntry.COLUMN_PNR,
                RailContract.RailEntry.COLUMN_AGE1,
                RailContract.RailEntry.COLUMN_AGE2,
                RailContract.RailEntry.COLUMN_AGE3,
                RailContract.RailEntry.COLUMN_AGE4,
                RailContract.RailEntry.COLUMN_AGE5,
                RailContract.RailEntry.COLUMN_AGE6,
                RailContract.RailEntry.COLUMN_GENDER1,
                RailContract.RailEntry.COLUMN_GENDER2,
                RailContract.RailEntry.COLUMN_GENDER3,
                RailContract.RailEntry.COLUMN_GENDER4,
                RailContract.RailEntry.COLUMN_GENDER5,
                RailContract.RailEntry.COLUMN_GENDER6,
                RailContract.RailEntry.COLUMN_DATE};
        String selection = RailContract.RailEntry.COLUMN_PNR+ "=?";
        String[] selectionArgs = {enterdpnr};
        Cursor cursor = getContentResolver().query(RailContract.RailEntry.CONTENT_URI, projection, selection, selectionArgs, null);
        int count=cursor.getCount();

        if(count == 0){
            Toast.makeText(this, "Nothing Found",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            ll.setVisibility(View.VISIBLE);
        }

        try{
            int nameColumnIndex = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_NAME1);
            int nameColumnIndex2 = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_NAME2);
            int nameColumnIndex3 = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_NAME3);
            int nameColumnIndex4 = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_NAME4);
            int nameColumnIndex5 = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_NAME5);
            int nameColumnIndex6 = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_NAME6);
            int fromColumnIndex = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_FROM);
            int toColumnIndex = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_TO);
            int pnrColumnPnr=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_PNR);
            int dateColumnIndex = cursor.getColumnIndex(RailContract.RailEntry.COLUMN_DATE);
            int ageColumnIndex=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_AGE1);
            int ageColumnIndex2=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_AGE2);
            int ageColumnIndex3=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_AGE3);
            int ageColumnIndex4=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_AGE4);
            int ageColumnIndex5=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_AGE5);
            int ageColumnIndex6=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_AGE6);
            int genColumnIndex=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_GENDER1);
            int genColumnIndex2=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_GENDER2);
            int genColumnIndex3=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_GENDER3);
            int genColumnIndex4=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_GENDER4);
            int genColumnIndex5=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_GENDER5);
            int genColumnIndex6=cursor.getColumnIndex(RailContract.RailEntry.COLUMN_GENDER6);

            while (cursor.moveToNext()) {

                String name = cursor.getString(nameColumnIndex);
                String name2 = cursor.getString(nameColumnIndex2);
                String name3 = cursor.getString(nameColumnIndex3);
                String name4 = cursor.getString(nameColumnIndex4);
                String name5 = cursor.getString(nameColumnIndex5);
                String name6 = cursor.getString(nameColumnIndex6);
                int tovalue = cursor.getInt(toColumnIndex);
                int datevalue = cursor.getInt(dateColumnIndex);
                int fromvalue = cursor.getInt(fromColumnIndex);
                int agevalue=cursor.getInt(ageColumnIndex);
                int agevalue2=cursor.getInt(ageColumnIndex2);
                int agevalue3=cursor.getInt(ageColumnIndex3);
                int agevalue4=cursor.getInt(ageColumnIndex4);
                int agevalue5=cursor.getInt(ageColumnIndex5);
                int agevalue6=cursor.getInt(ageColumnIndex6);
                int genvalue=cursor.getInt(genColumnIndex);
                int genvalue2=cursor.getInt(genColumnIndex2);
                int genvalue3=cursor.getInt(genColumnIndex3);
                int genvalue4=cursor.getInt(genColumnIndex4);
                int genvalue5=cursor.getInt(genColumnIndex5);
                int genvalue6=cursor.getInt(genColumnIndex6);
                String pnrvalue=cursor.getString(pnrColumnPnr);

                forName.setText(name);




                switch (fromvalue){
                    case RailContract.RailEntry.COLUMN_FROM_FD:
                        forFrom.setText(R.string.from_fd);
                        break;
                    case RailContract.RailEntry.COLUMN_FROM_ALD:
                        forFrom.setText(R.string.from_ald);
                        break;
                    case RailContract.RailEntry.COLUMN_FROM_KANPUR:
                        forFrom.setText(R.string.from_knp);
                        break;
                }
                switch (tovalue){
                    case RailContract.RailEntry.COLUMN_TO_FD:
                        forTo.setText(R.string.to_fd);
                        break;
                    case RailContract.RailEntry.COLUMN_TO_ALD:
                        forTo.setText(R.string.to_ald);
                        break;
                    case RailContract.RailEntry.COLUMN_TO_KANPUR:
                        forTo.setText(R.string.to_knp);
                        break;
                }
                switch (genvalue){
                    case RailContract.RailEntry.COLUMN_GENDER_MALE:
                        forGen.setText(R.string.gender_male);
                        break;
                    case RailContract.RailEntry.COLUMN_GENDER_FEMALE:
                        forGen.setText(R.string.gender_female);
                        break;
                    case RailContract.RailEntry.UNKNOWN:
                        forGen.setText("");
                        break;
                }
                switch (genvalue2){
                    case RailContract.RailEntry.COLUMN_GENDER_MALE:
                        Gen2=getString(R.string.gender_m);
                        break;
                    case RailContract.RailEntry.COLUMN_GENDER_FEMALE:
                        Gen2=getString(R.string.gender_f);
                        break;
                    case RailContract.RailEntry.UNKNOWN:
                        Gen2="";
                        break;
                }
                switch (genvalue3){
                    case RailContract.RailEntry.COLUMN_GENDER_MALE:
                        Gen3=getString(R.string.gender_m);
                        break;
                    case RailContract.RailEntry.COLUMN_GENDER_FEMALE:
                        Gen3=getString(R.string.gender_f);
                        break;
                    case RailContract.RailEntry.UNKNOWN:
                        Gen3="";
                        break;
                }
                switch (genvalue4){
                    case RailContract.RailEntry.COLUMN_GENDER_MALE:
                        Gen4=getString(R.string.gender_m);
                        break;
                    case RailContract.RailEntry.COLUMN_GENDER_FEMALE:
                        Gen4=getString(R.string.gender_f);
                        break;
                    case RailContract.RailEntry.UNKNOWN:
                        Gen4="";
                        break;
                }
                switch (genvalue5){
                    case RailContract.RailEntry.COLUMN_GENDER_MALE:
                        Gen5=getString(R.string.gender_m);
                        break;
                    case RailContract.RailEntry.COLUMN_GENDER_FEMALE:
                        Gen5=getString(R.string.gender_f);
                        break;
                    case RailContract.RailEntry.UNKNOWN:
                        Gen5="";
                        break;
                }
                switch (genvalue6){
                    case RailContract.RailEntry.COLUMN_GENDER_MALE:
                        Gen6=getString(R.string.gender_m);
                        break;
                    case RailContract.RailEntry.COLUMN_GENDER_FEMALE:
                        Gen6=getString(R.string.gender_f);
                        break;
                    case RailContract.RailEntry.UNKNOWN:
                        Gen6="";
                        break;
                }
                forDate.setText(Integer.toString(datevalue));
                forPnr.setText(pnrvalue);
                forAge.setText(Integer.toString(agevalue));

                if(agevalue2==0){
                    age2="";
                }
                else{
                    age2=Integer.toString(agevalue2);
                }
                if(agevalue3==0){
                    age3="";
                }
                else{
                    age3=Integer.toString(agevalue3);
                }
                if(agevalue4==0){
                    age4="";
                }
                else{
                    age4=Integer.toString(agevalue4);
                }
                if(agevalue5==0){
                    age5="";
                }
                else{
                    age5=Integer.toString(agevalue5);
                }

                if(agevalue6==0){
                    age6="";
                }
                else{
                    age6=Integer.toString(agevalue6);
                }

                forOther.setText(name2+" "+age2+" "+ Gen2 +"  "+ name3+" "+age3+" "+ Gen3+"  "+name4+" "+age4+" "+ Gen4+"  "+name5+" "+age5+" "+ Gen5+"  "+name6+" "+age6+" "+ Gen6 );
            }}


        finally{

            cursor.close();

        }
    }
}
