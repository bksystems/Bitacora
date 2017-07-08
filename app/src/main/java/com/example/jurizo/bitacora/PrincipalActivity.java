package com.example.jurizo.bitacora;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jurizo.bitacora.Core.CoreBitacora.Controls.AdapterListViewVisit;
import com.example.jurizo.bitacora.Core.CoreBitacora.Controls.AdapterSpinnerCustom;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Visits;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.SyncManager;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityVisita;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private Activity context;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setToolbar();
        loadUsers();
        loadVisitas(0);
    }


    private void loadUsers() {
        List<EntityUser> getUsersFilters = new ArrayList<>();
        DAO_Users dao_users = new DAO_Users(context);
        List<EntityUser> users = dao_users.getUsers();
        if(users.size() > 1){
            EntityUser usr = new EntityUser(0,0,"Todos", "", "", "", "", 0, 0, "", "");
            getUsersFilters.add(usr);
        }
        getUsersFilters.addAll(users);
        final Spinner spnUsers = (Spinner) findViewById(R.id.principal_spinner_user);
        spnUsers.setPrompt("Selecciona colaborador");
        AdapterSpinnerCustom adapterSpinner = new AdapterSpinnerCustom(context, getUsersFilters);
        spnUsers.setAdapter(adapterSpinner);
        spnUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               EntityUser getUsr = (EntityUser) spnUsers.getItemAtPosition(position);
                if(getUsr != null){
                    ProgressDialog pgd = new ProgressDialog(context);
                    pgd.show();
                    pgd.setContentView(R.layout.custom_progressdialog);
                    loadVisitas(getUsr.getId());
                    pgd.dismiss();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_action_bar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Bitacora");
        bar.setSubtitle("Visitas realizadas");
        bar.setDisplayHomeAsUpEnabled(true);

    }

    private void loadVisitas(int idUser) {
       lstView = (ListView)findViewById(R.id.visit_lst_visitas);
        DAO_Visits dao_visits = new DAO_Visits(context);
        List<EntityVisita> arrayVisit = null;
        if(idUser <= 0){
            arrayVisit = dao_visits.getVisitas();
        }else {
            arrayVisit = dao_visits.getVisitasByUserId(idUser);
        }
        AdapterListViewVisit adapterVisit = new AdapterListViewVisit(context, R.layout.layout_visits, R.id.layout_visita_id, arrayVisit);
        lstView.setAdapter(adapterVisit);
        adapterVisit.notifyDataSetChanged();
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



}
