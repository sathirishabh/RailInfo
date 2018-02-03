package com.example.android.railinfo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.railinfo.data.RailContract;
import com.example.android.railinfo.data.RailContract.RailEntry;

import java.util.ArrayList;


/**
 * Created by sathirishabh on 20-07-2017.
 */

public class EditorActivity extends AppCompatActivity {


    private EditText mdate;
    private EditText adname1;
    private EditText adname2;
    private EditText adname3;
    private EditText adname4;
    private EditText adage1;
    private EditText adage2;
    private EditText adage3;
    private EditText adage4;
    private Spinner adgender1;
    private Spinner adgender2;
    private Spinner adgender3;
    private Spinner adgender4;
    private EditText chname1;
    private EditText chname2;
    private EditText chage1;
    private EditText chage2;
    private Spinner chgender1;
    private Spinner chgender2;

    private Uri mContentUri;
    private int mFrom;
    private int mTO;
    private int adGEN1;
    private int adGEN2;
    private int adGEN3;
    private int adGEN4;
    private int chGEN1;
    private int chGEN2;
    Spinner trainname;
    String TrainName;
    ArrayList<String> itemIds;


    ContentValues values;
    private boolean mPetHasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPetHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        adname1 =(EditText)findViewById(R.id.name1);
        adname2 =(EditText)findViewById(R.id.name2);
        adname3 =(EditText)findViewById(R.id.name3);
        adname4 =(EditText)findViewById(R.id.name4);
        adage1=(EditText)findViewById(R.id.age1);
        adage2=(EditText)findViewById(R.id.age2);
        adage3=(EditText)findViewById(R.id.age3);
        adage4=(EditText)findViewById(R.id.age4);
        adgender1=(Spinner)findViewById(R.id.adultgen1);
        adgender2=(Spinner)findViewById(R.id.adultgen2);
        adgender3=(Spinner)findViewById(R.id.adultgen3);
        adgender4=(Spinner)findViewById(R.id.adultgen4);

        mdate=(EditText)findViewById(R.id.mdate);
        chname1=(EditText)findViewById(R.id.childname1);
        chname2=(EditText)findViewById(R.id.childname2);
        chage1=(EditText)findViewById(R.id.childage1);
        chage2=(EditText)findViewById(R.id.childage2);
        chgender1=(Spinner)findViewById(R.id.childgen1);
        chgender2=(Spinner)findViewById(R.id.childgen2);
        itemIds=getIntent().getExtras().getStringArrayList("List");
         mFrom=getIntent().getExtras().getInt("From");
         mTO=getIntent().getExtras().getInt("To");
        TextView forFrom=(TextView)findViewById(R.id.frm);
        TextView forTo=(TextView)findViewById(R.id.dest);
        trainname = (Spinner) findViewById(R.id.trainname);

        switch (mFrom){
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
        switch (mTO){
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




        adname1.setOnTouchListener(mTouchListener);

        mdate.setOnTouchListener(mTouchListener);

        setupSpinnerGender();
        setupSpinnertrainame();
    }
    private void setupSpinnertrainame() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, itemIds);

        // Specify dropdown layout style - simple list view with 1 item per line
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinnert
        trainname.setAdapter(dataAdapter);

        // Set the integer m
        // Selected to the constant values
        trainname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TrainName = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(TrainName)) {


                }

            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

             TrainName="Nothing Found";
            }
        });
    }


    private void setupSpinnerGender() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        adgender1.setAdapter(genderSpinnerAdapter);
        adgender2.setAdapter(genderSpinnerAdapter);
        adgender3.setAdapter(genderSpinnerAdapter);
        adgender4.setAdapter(genderSpinnerAdapter);
        chgender1.setAdapter(genderSpinnerAdapter);
        chgender2.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        adgender1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        adGEN1=RailEntry.COLUMN_GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        adGEN1= RailEntry.COLUMN_GENDER_FEMALE;
                    }
                    else {
                        adGEN1 = RailEntry.UNKNOWN;
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                adGEN1 = 0; // Unknown
            }
        });
        adgender2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        adGEN2=RailEntry.COLUMN_GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        adGEN2= RailEntry.COLUMN_GENDER_FEMALE;
                    }
                    else {
                        adGEN2 = RailEntry.UNKNOWN;
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                adGEN2  = 0; // Unknown
            }
        });
        adgender3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        adGEN3=RailEntry.COLUMN_GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        adGEN3= RailEntry.COLUMN_GENDER_FEMALE;
                    }
                    else {
                        adGEN3 = RailEntry.UNKNOWN;
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                adGEN3 = 0; // Unknown
            }
        });
        adgender4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        adGEN4=RailEntry.COLUMN_GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        adGEN4= RailEntry.COLUMN_GENDER_FEMALE;
                    }
                    else {
                        adGEN4 = RailEntry.UNKNOWN;
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                adGEN4 = 0; // Unknown
            }
        });
        chgender1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        chGEN1=RailEntry.COLUMN_GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        chGEN1= RailEntry.COLUMN_GENDER_FEMALE;
                    }
                    else {
                        chGEN1 = RailEntry.UNKNOWN;
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                chGEN1 = 0; // Unknown
            }
        });
        chgender2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        chGEN2=RailEntry.COLUMN_GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        chGEN2= RailEntry.COLUMN_GENDER_FEMALE;
                    }
                    else {
                        chGEN2 = RailEntry.UNKNOWN;
                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                chGEN2 = 0; // Unknown
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mPetHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_booking_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_booking, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_details:
                int date=0;
                int age1=0;
                int age2=0;
                int age3=0;
                int age4=0;
                int age5=0;
                int age6=0;
                char[] genreratedPnr=GenerateOtp.OTP(10);
                String pnr=String.valueOf(genreratedPnr);
               RadioGroup birthradioGroup = (RadioGroup) findViewById(R.id.birthradiogroup);
                RadioGroup classradioGroup = (RadioGroup) findViewById(R.id.classradiogroup);
                int selectedidbirth=birthradioGroup.getCheckedRadioButtonId();
                int selectedidclass=classradioGroup.getCheckedRadioButtonId();
                RadioButton radioButtonbirth = (RadioButton) findViewById(selectedidbirth);
                RadioButton radioButtonclass=(RadioButton)findViewById(selectedidclass);
                String Birth=radioButtonbirth.getText().toString().trim();
                String classselected=radioButtonclass.getText().toString().trim();



                String nameString1= adname1.getText().toString().trim();
                String dateString=mdate.getText().toString().trim();

                if (!TextUtils.isEmpty(dateString)) {
                    date = Integer.parseInt(dateString);
                }


                String nameString2= adname2.getText().toString().trim();
                String nameString3= adname3.getText().toString().trim();
                String nameString4= adname4.getText().toString().trim();
                String nameString5= chname1.getText().toString().trim();
                String nameString6= chname2.getText().toString().trim();

                String ageString1=adage1.getText().toString().trim();
                String ageString2=adage2.getText().toString().trim();
                String ageString3=adage3.getText().toString().trim();
                String ageString4=adage4.getText().toString().trim();
                String ageString5=chage1.getText().toString().trim();
                String ageString6=chage2.getText().toString().trim();


                if (!TextUtils.isEmpty(ageString1)) {
                    age1 = Integer.parseInt(ageString1);
                }
                if (!TextUtils.isEmpty(ageString2)) {
                    age2 = Integer.parseInt(ageString2);
                }
                if (!TextUtils.isEmpty(ageString3)) {
                    age3 = Integer.parseInt(ageString3);
                }
                if (!TextUtils.isEmpty(ageString4)) {
                    age4 = Integer.parseInt(ageString4);
                }
                if (!TextUtils.isEmpty(ageString4)) {
                    age5 = Integer.parseInt(ageString5);
                }
                if (!TextUtils.isEmpty(ageString4)) {
                    age6 = Integer.parseInt(ageString6);
                }
                Intent intent=new Intent(this,OTPActivity.class);
                intent.putExtra("From",mFrom);
                intent.putExtra("To",mTO);
                intent.putExtra("Birth",Birth);
                intent.putExtra("Class",classselected);
                intent.putExtra("TrainName",TrainName);
                intent.putExtra("Date",date);
                intent.putExtra("Pnr",pnr);
                intent.putExtra("Name1",nameString1);
                intent.putExtra("Age1",age1);
                intent.putExtra("Gender1",adGEN1);
                intent.putExtra("Name2",nameString2);
                intent.putExtra("Age2",age2);
                intent.putExtra("Gender2",adGEN2);
                intent.putExtra("Name3",nameString3);
                intent.putExtra("Age3",age3);
                intent.putExtra("Gender3",adGEN3);
                intent.putExtra("Name4",nameString4);
                intent.putExtra("Age4",age4);
                intent.putExtra("Gender4",adGEN4);
                intent.putExtra("Name5",nameString5);
                intent.putExtra("Age5",age5);
                intent.putExtra("Gender5",chGEN1);
                intent.putExtra("Name6",nameString6);
                intent.putExtra("Age6",age6);
                intent.putExtra("Gender6",chGEN2);

                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }}
