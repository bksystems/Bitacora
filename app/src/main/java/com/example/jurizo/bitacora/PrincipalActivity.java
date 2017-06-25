package com.example.jurizo.bitacora;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.service.carrier.CarrierService;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        prepareDatabase();
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_action_bar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Bitacora");
        bar.setSubtitle("Visitas realizadas");
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent visit_intent = new Intent(this, VisitActivity.class);
                startActivity(visit_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void prepareDatabase() {
      //  DBHelper dbHelper = new DBHelper(this,"bitacora.db", 1);
      //  SQLiteDatabase db = dbHelper.getWritableDatabase();
      //  dbHelper.onCreate(db);

    }


}
