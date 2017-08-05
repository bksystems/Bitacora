package com.example.jurizo.bitacora;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SessionManagement;

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
    private Button btnChangePassword;
    public EditText passwordNueva;
    private EditText passwordActual;
    private EditText passwordConfirm;

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
        btnChangePassword = (Button) findViewById(R.id.settings_btn_user_change_password);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilderChangePassword = new AlertDialog.Builder(SettingsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.change_password, null);

                passwordActual = (EditText)mView.findViewById(R.id.login_user_change_password_actual);
                passwordNueva = (EditText)mView.findViewById(R.id.login_user_change_password_nueva);
                passwordConfirm = (EditText)mView.findViewById(R.id.login_user_change_password_confirmar);
                Button passwordBtnConfirm = (Button)mView.findViewById(R.id.login_user_change_button_confirmar);
                ImageButton passwordBtnCancel = (ImageButton)mView.findViewById(R.id.login_user_change_button_cancelar);

                mBuilderChangePassword.setView(mView);
                final AlertDialog dialog = mBuilderChangePassword.create();

                passwordBtnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(NewsPasswordsValidate(passwordActual.getText().toString(), passwordNueva.getText().toString(), passwordConfirm.getText().toString())){
                            Toast.makeText(context, "Cambio", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                passwordBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialog != null){
                            dialog.dismiss();
                        }
                    }
                });

                passwordNueva.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if(s.length()!= 0){
                                NewsPasswordsValidate(passwordActual.getText().toString(), passwordNueva.getText().toString(), passwordConfirm.getText().toString());
                            }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                passwordConfirm.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.length()!= 0){
                            NewsPasswordsValidate(passwordActual.getText().toString(), passwordNueva.getText().toString(), passwordConfirm.getText().toString());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                dialog.show();

            }
        });
    }

    private boolean NewsPasswordsValidate(String pa, String np, String cp) {

            boolean valid = true;
            String actualPas = pa.toString();
            String newPass = np.toString();
            String confirmPass = cp.toString();

            if(actualPas.isEmpty()){
                passwordActual.setError("Por favor ingresa tu contraseña actual");
                valid = false;
            }else{
                passwordActual.setError(null);
            }

            if(newPass.isEmpty()){
                passwordNueva.setError("Por favor ingresa tu nueva contraseña");
                valid = false;
            }else{
                passwordNueva.setError(null);
            }

            if(confirmPass.isEmpty()){
                passwordConfirm.setError("Por favor confirma tu nueva contraseña");
                valid = false;
            }else{
                passwordConfirm.setError(null);
            }

            if(!confirmPass.toString().equals(newPass.toString())){
                passwordConfirm.setError("Las contraseñas no coinciden");
                passwordNueva.setError("Las contraseñas no coinciden");
                valid = false;
            }else{
                passwordConfirm.setError(null);
                passwordNueva.setError(null);
            }

            return valid;

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
        /*SessionManagement sessionManagement = new SessionManagement(context);
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
        }*/
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
