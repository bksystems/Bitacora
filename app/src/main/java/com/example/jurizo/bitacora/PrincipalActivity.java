package com.example.jurizo.bitacora;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Visits;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.SyncManager;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityVisita;

import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        loadVisitas();
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
            case R.id.action_sync:
                SyncManager syncManager = new SyncManager(context);
                syncManager.execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void loadVisitas() {
        DAO_Visits dao_visits = new DAO_Visits(context);
        List<EntityVisita> visitas = dao_visits.getVisitas();

    }


}
