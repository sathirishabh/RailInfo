        package com.example.android.railinfo;


        import android.app.PendingIntent;
        import android.content.ContentUris;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.content.Loader;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.android.railinfo.data.RailContract;
        import com.example.android.railinfo.data.RailDbHelper;
        import com.example.android.railinfo.data.RailContract.RailEntry;


public class CatalogActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor> {
    private RailDbHelper mDbHelper;
    private static final int RAIL_LOADER = 0;
    RailCursorAdapter mcursoradapter;
    Intent intent;

    private static boolean LOAD=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        intent = new Intent(this, LoginActivity.class);

        if(LOAD){

            startActivity(intent);
            LOAD=false;

        }
        mDbHelper = new RailDbHelper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, SearchActivity.class);
                startActivity(intent);


            }
        });
        ListView petListView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);
        mcursoradapter = new RailCursorAdapter(this, null);
        mDbHelper = new RailDbHelper(this);
        petListView.setAdapter(mcursoradapter);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CatalogActivity.this,ItemActivity.class);
                Uri ContentUri= ContentUris.withAppendedId(RailEntry.CONTENT_URI,id);
                intent.setData(ContentUri);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(RAIL_LOADER, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.admin_panel:


                Intent admin=new Intent(this,AdminActivity.class);
                startActivity(admin);

                return true;
            case R.id.cancel_all_entries:
                //do for nothing now
                deleteAllPets();

                return true;

            case R.id.show_pnr_status:
                //pnr status;
                Intent i2=new Intent(this,PnrActivity.class);
                startActivity(i2);


                return true;
            case R.id.logout:

                startActivity(intent);
                return true;
            case R.id.about_dev:
                Intent about=new Intent(this,AboutActivity.class);
                startActivity(about);

                return true;



        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(RailEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection={RailEntry._ID,RailEntry.COLUMN_NAME1,RailEntry.COLUMN_DATE};
        return new android.content.CursorLoader(this,RailEntry.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mcursoradapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mcursoradapter.swapCursor(null);

    }


}
