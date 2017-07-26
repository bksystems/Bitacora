package com.example.jurizo.bitacora;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.jurizo.bitacora.Core.BitacoraCore.Controls.AdapterListViewVisit;
import com.example.jurizo.bitacora.Core.BitacoraCore.Controls.AdapterSpinnerCustom;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.DAO_Visits;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SessionManagement;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices.SyncManager;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityArea;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityPuesto;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityVisita;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private Activity context;
    //private ListView lstView;
    private SwipeMenuListView lstView;

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
            EntityPuesto puesto = new EntityPuesto(0, "");
            EntityArea area = new EntityArea(0, "");
            EntityUser usr = new EntityUser(0,0,"Todos", "", "", "", "", 0, 0, puesto, area, "", "");
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
       lstView = (SwipeMenuListView) findViewById(R.id.visit_lst_visitas);
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

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(context);
                editItem.setBackground(new ColorDrawable(Color.rgb(242, 144, 58)));
                editItem.setWidth(150);
                editItem.setTitle("Editar");
                editItem.setIcon(R.drawable.ic_mode_edit_black_24dp);
                editItem.setTitleSize(14);
                editItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(editItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                deleteItem.setBackground(new ColorDrawable(Color.rgb(236, 51, 0)));
                deleteItem.setWidth(150);
                deleteItem.setTitle("Eliminar");
                deleteItem.setIcon(R.drawable.ic_delete_forever_black_24dp);
                deleteItem.setTitleSize(14);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        lstView.setMenuCreator(creator);

        lstView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.action_add:
                Intent visit_intent = new Intent(this, VisitActivity.class);
                startActivity(visit_intent);
                return true;
            case R.id.action_logout:
                SessionManagement sessionManagement = new SessionManagement(context);
                HashMap<String, String> usr = sessionManagement.getUserDetails();
                sessionManagement.logoutUser();
                return true;
            case R.id.action_sync:
                SyncManager syncManager = new SyncManager(context);
                syncManager.execute();
                return true;
            case R.id.action_options:
                Intent intentSettings = new Intent(PrincipalActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                return true;
            case R.id.action_office_ubication:
                Intent intentUbicationOS = new Intent(PrincipalActivity.this, ActivityOficinasUbication.class);
                startActivity(intentUbicationOS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



}
