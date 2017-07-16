package com.example.jurizo.bitacora;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SessionManagement;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.SyncManager;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private Context context;
    private TextView txtNomina;
    private TextView txtNombre;
    private TextView txtPuesto;
    private TextView txtArea;
    private TextView txtEmail;
    private TextView txtEstatus;
    private ListView listViewColDependientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        InitToolBar();
        InitControls();
        LoadUserSetings();
    }

    private void InitControls() {
        txtNomina =  (TextView) findViewById(R.id.settings_text_user_nomina);
        txtNombre =  (TextView) findViewById(R.id.settings_text_user_nombre);
        txtPuesto =  (TextView) findViewById(R.id.settings_text_user_puesto);
        txtArea =  (TextView) findViewById(R.id.settings_text_user_area);
        txtEmail =  (TextView) findViewById(R.id.settings_text_user_email);
        txtEstatus =  (TextView) findViewById(R.id.settings_text_user_estatus);
        listViewColDependientes = (ListView)findViewById(R.id.settings_lstv_colaboradores);
    }

    private void InitToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.settings_bar_action);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Bitacora");
        bar.setSubtitle("Configuración de aplicación");
        bar.setDisplayHomeAsUpEnabled(true);
    }

    private void LoadUserSetings() {
        SessionManagement sessionManagement = new SessionManagement(context);
        HashMap<String, String> usr = sessionManagement.getUserDetails();
        String user_id = usr.get("user_id");
        DAO_Users daoUsers = new DAO_Users(context);
        EntityUser userSelection = daoUsers.getUserById(Integer.parseInt(user_id));
        List<EntityUser> usersDependientes = daoUsers.getUsers();
        if(userSelection != null ){
            txtNomina.setText("Nómina: " + String.valueOf(userSelection.getNomina()));
            txtNombre.setText("Nombre: " + userSelection.getApellido_paterno()+" "+ userSelection.getApellido_materno() + " " + userSelection.getNombres());
            txtPuesto.setText("Puesto: " + userSelection.getPuesto().getPuesto());
            txtEmail.setText("Email: " + userSelection.getEmail());
            txtArea.setText("Área: " + userSelection.getArea().getArea());
            String estado = "";
            if(userSelection.getId_status() > 0){
                estado = "Activo";
                txtEstatus.setTextColor(Color.rgb(0, 100, 0));
            }else{
                estado = "Inactivo";
                txtEstatus.setTextColor(Color.rgb(100, 0, 0));
            }
            txtEstatus.setText("Estatus: "+ estado);
            List<String> colDependientes = new ArrayList<>();
            for (EntityUser usersA: usersDependientes) {
                if(usersA.getId() != userSelection.getId()){
                    colDependientes.add(String.valueOf(usersA.getNomina()) + " - " + usersA.getApellido_paterno()+" "+ usersA.getApellido_materno() + " " + usersA.getNombres() + " (" + usersA.getPuesto().getPuesto() + ")");
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    colDependientes );
            listViewColDependientes.setAdapter(arrayAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
