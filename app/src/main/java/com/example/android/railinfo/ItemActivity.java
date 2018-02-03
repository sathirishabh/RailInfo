package com.example.android.railinfo;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.railinfo.data.RailContract.RailEntry;

import org.w3c.dom.Text;

/**
 * Created by sathirishabh on 22-07-2017.
 */

public class ItemActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Uri mContentUri;
    private static final int PET_LOADER = 0;


    private TextView forName;
    private TextView forTrainname;
    private TextView forBirth;
    private TextView forClass;
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
        setContentView(R.layout.item_activity);
        Intent intent = getIntent();
        mContentUri = intent.getData();
        forName=(TextView)findViewById(R.id.for_name);
        forTrainname=(TextView)findViewById(R.id.for_trainname) ;
        forBirth=(TextView)findViewById(R.id.for_birth);
        forClass=(TextView)findViewById(R.id.for_class);

        forFrom=(TextView)findViewById(R.id.for_from);
        forTo=(TextView)findViewById(R.id.for_to);
        forDate=(TextView)findViewById(R.id.for_date);
        forPnr=(TextView)findViewById(R.id.for_pnr);
        forAge=(TextView)findViewById(R.id.for_age);
        forOther=(TextView)findViewById(R.id.for_other);

        forGen=(TextView)findViewById(R.id.for_gen);

        getLoaderManager().initLoader(PET_LOADER, null, this);
    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // all columns from the pet table
        String[] projection = {
                RailEntry._ID,
                RailEntry.COLUMN_NAME1,
                RailEntry.COLUMN_NAME2,
                RailEntry.COLUMN_NAME3,
                RailEntry.COLUMN_NAME4,
                RailEntry.COLUMN_NAME5,
                RailEntry.COLUMN_NAME6,
                RailEntry.COLUMN_FROM,
                RailEntry.COLUMN_TO,
                RailEntry.COLUMN_TRAINNAME,
                RailEntry.COLUMN_BIRTH,
                RailEntry.COLUMN_CLASS,
                RailEntry.COLUMN_PNR,
                RailEntry.COLUMN_AGE1,
                RailEntry.COLUMN_AGE2,
                RailEntry.COLUMN_AGE3,
                RailEntry.COLUMN_AGE4,
                RailEntry.COLUMN_AGE5,
                RailEntry.COLUMN_AGE6,
                RailEntry.COLUMN_GENDER1,
                RailEntry.COLUMN_GENDER2,
                RailEntry.COLUMN_GENDER3,
                RailEntry.COLUMN_GENDER4,
                RailEntry.COLUMN_GENDER5,
                RailEntry.COLUMN_GENDER6,
                RailEntry.COLUMN_DATE};

        // This loader will execute the ContentProvider's query method on a background thread
        return new android.content.CursorLoader(this,   // Parent activity context
                mContentUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }


    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        String age2;
        String age3;
        String age4;
        String age5;
        String age6;


        if(cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(RailEntry.COLUMN_NAME1);
            int nameColumnIndex2 = cursor.getColumnIndex(RailEntry.COLUMN_NAME2);
            int nameColumnIndex3 = cursor.getColumnIndex(RailEntry.COLUMN_NAME3);
            int nameColumnIndex4 = cursor.getColumnIndex(RailEntry.COLUMN_NAME4);
            int nameColumnIndex5 = cursor.getColumnIndex(RailEntry.COLUMN_NAME5);
            int nameColumnIndex6 = cursor.getColumnIndex(RailEntry.COLUMN_NAME6);
            int fromColumnIndex = cursor.getColumnIndex(RailEntry.COLUMN_FROM);
            int toColumnIndex = cursor.getColumnIndex(RailEntry.COLUMN_TO);
            int pnrColumnPnr=cursor.getColumnIndex(RailEntry.COLUMN_PNR);
            int trainnameColumnIndex=cursor.getColumnIndex(RailEntry.COLUMN_TRAINNAME);
            int birthColumnIndex=cursor.getColumnIndex(RailEntry.COLUMN_BIRTH);
            int classColumnIndex=cursor.getColumnIndex(RailEntry.COLUMN_CLASS);
            int dateColumnIndex = cursor.getColumnIndex(RailEntry.COLUMN_DATE);
            int ageColumnIndex=cursor.getColumnIndex(RailEntry.COLUMN_AGE1);
            int ageColumnIndex2=cursor.getColumnIndex(RailEntry.COLUMN_AGE2);
            int ageColumnIndex3=cursor.getColumnIndex(RailEntry.COLUMN_AGE3);
            int ageColumnIndex4=cursor.getColumnIndex(RailEntry.COLUMN_AGE4);
            int ageColumnIndex5=cursor.getColumnIndex(RailEntry.COLUMN_AGE5);
            int ageColumnIndex6=cursor.getColumnIndex(RailEntry.COLUMN_AGE6);
            int genColumnIndex=cursor.getColumnIndex(RailEntry.COLUMN_GENDER1);
            int genColumnIndex2=cursor.getColumnIndex(RailEntry.COLUMN_GENDER2);
            int genColumnIndex3=cursor.getColumnIndex(RailEntry.COLUMN_GENDER3);
            int genColumnIndex4=cursor.getColumnIndex(RailEntry.COLUMN_GENDER4);
            int genColumnIndex5=cursor.getColumnIndex(RailEntry.COLUMN_GENDER5);
            int genColumnIndex6=cursor.getColumnIndex(RailEntry.COLUMN_GENDER6);


            String name = cursor.getString(nameColumnIndex);
            String name2 = cursor.getString(nameColumnIndex2);
            String name3 = cursor.getString(nameColumnIndex3);
            String name4 = cursor.getString(nameColumnIndex4);
            String name5 = cursor.getString(nameColumnIndex5);
            String name6 = cursor.getString(nameColumnIndex6);
            String trainname=cursor.getString(trainnameColumnIndex);
            String birthvalue=cursor.getString(birthColumnIndex);
            String classvalue=cursor.getString(classColumnIndex);
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
            forTrainname.setText(trainname);
            forBirth.setText(birthvalue);
            forClass.setText(classvalue);




            switch (fromvalue){
                case RailEntry.COLUMN_FROM_FD:
                    forFrom.setText(R.string.from_fd);
                    break;
                case RailEntry.COLUMN_FROM_ALD:
                    forFrom.setText(R.string.from_ald);
                    break;
                case RailEntry.COLUMN_FROM_KANPUR:
                    forFrom.setText(R.string.from_knp);
                    break;
            }
            switch (tovalue){
                case RailEntry.COLUMN_TO_FD:
                    forTo.setText(R.string.to_fd);
                    break;
                case RailEntry.COLUMN_TO_ALD:
                    forTo.setText(R.string.to_ald);
                    break;
                case RailEntry.COLUMN_TO_KANPUR:
                    forTo.setText(R.string.to_knp);
                    break;
            }
            switch (genvalue){
                case RailEntry.COLUMN_GENDER_MALE:
                    forGen.setText(R.string.gender_male);
                    break;
                case RailEntry.COLUMN_GENDER_FEMALE:
                    forGen.setText(R.string.gender_female);
                    break;
                case RailEntry.UNKNOWN:
                    forGen.setText("");
                    break;
            }
            switch (genvalue2){
                case RailEntry.COLUMN_GENDER_MALE:
                    Gen2=getString(R.string.gender_m);
                    break;
                case RailEntry.COLUMN_GENDER_FEMALE:
                    Gen2=getString(R.string.gender_f);
                    break;
                case RailEntry.UNKNOWN:
                    Gen2="";
                    break;
            }
            switch (genvalue3){
                case RailEntry.COLUMN_GENDER_MALE:
                      Gen3=getString(R.string.gender_m);
                    break;
                case RailEntry.COLUMN_GENDER_FEMALE:
                    Gen3=getString(R.string.gender_f);
                    break;
                case RailEntry.UNKNOWN:
                    Gen3="";
                    break;
            }
            switch (genvalue4){
                case RailEntry.COLUMN_GENDER_MALE:
                    Gen4=getString(R.string.gender_m);
                    break;
                case RailEntry.COLUMN_GENDER_FEMALE:
                      Gen4=getString(R.string.gender_f);
                    break;
                case RailEntry.UNKNOWN:
                    Gen4="";
                    break;
            }
            switch (genvalue5){
                case RailEntry.COLUMN_GENDER_MALE:
                     Gen5=getString(R.string.gender_m);
                    break;
                case RailEntry.COLUMN_GENDER_FEMALE:
                     Gen5=getString(R.string.gender_f);
                    break;
                case RailEntry.UNKNOWN:
                    Gen5="";
                    break;
            }
            switch (genvalue6){
                case RailEntry.COLUMN_GENDER_MALE:
                     Gen6=getString(R.string.gender_m);
                    break;
                case RailEntry.COLUMN_GENDER_FEMALE:
                     Gen6=getString(R.string.gender_f);
                    break;
                case RailEntry.UNKNOWN:
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
        }

    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.cancel_dialog_msg);
        builder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                CancelTicket();

            }
        });
        builder.setNegativeButton(R.string.discard, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
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

    /**
     * Perform the deletion of the pet in the database.
     */
    private void CancelTicket() {
        if (mContentUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(mContentUri, null, null);
            //The delete method, like update, returns the number of rows deleted. I can see if the delete was successful by checking whether 0 rows were deleted. If zero rows were deleted, then the delete was not successful and I’ll show a toast that says “ Error with deleting pet”. Otherwise the operation was successful, and I pop up a toast that says “Pet deleted”.
              Intent refresh=new Intent(this,CatalogActivity.class);
                   startActivity(refresh);
            finish();
            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.error_with_cancelling),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, "Deleted!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancel_one:

              showDeleteConfirmationDialog();

                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
