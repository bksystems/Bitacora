package com.example.jurizo.bitacora;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SessionManagement;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.SyncManager;

import java.util.HashMap;

public class VisitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        setToolbar();

        BottomNavigationView bottomNavigationView = (BottomNavigationView ) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_visit_general:
                        selectedFragment = VisitGeneralFragment.newInstance();
                        break;
                    case R.id.nav_visit_seguimiento:
                        selectedFragment = VisitSeguimientoFragment.newInstance();
                        break;
                    case R.id.nav_visit_movilidad:
                        selectedFragment = VisitMovilidadFragment.newInstance();
                        break;
                    case R.id.nav_visit_pl:
                        selectedFragment = VisitLogisticaFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.visit_fragment_content, selectedFragment);
                transaction.commit();
                return true;
            }
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.visit_fragment_content, VisitGeneralFragment.newInstance());
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.visit_action_bar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Ejecución de visita");
        bar.setSubtitle("Capturar información");
        bar.setDisplayHomeAsUpEnabled(true);
    }
}
