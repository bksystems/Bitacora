package com.example.jurizo.bitacora;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jurizo.bitacora.Core.CoreBitacora.Controls.LoginActionListener;
import com.example.jurizo.bitacora.Core.CoreBitacora.Controls.Utils;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Oficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.ConfigServerConnection;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.HttpHandler;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices.SyncAuxHandlerParse;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private Context context;

    @InjectView(R.id.login_user_name) EditText login_user_name;
    @InjectView(R.id.login_user_password) EditText login_user_password;
    @InjectView(R.id.login_user_signup) Button login_user_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        ButterKnife.inject(this);

        PrepareDataBase();

        login_user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_name = login_user_name.getText().toString();
                    String user_password = Utils.EncryptPassword(login_user_password.getText().toString());
                    String serialNumber = getSerialNumber();
                    String imei = getIMEI();
                    LoginSync loginSync = (LoginSync) new LoginSync(context, new LoginActionListener() {
                        @Override
                        public void onActionListener(EntityUser user) {
                            Toast.makeText(context, "Usuario correcto", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, PrincipalActivity.class));
                        }
                    }).execute(user_name, user_password, serialNumber, imei);
                }
            }
        });
    }

    private void PrepareDataBase() {
        try {
            //DBHelper dbHelper = new DBHelper(this);
            //SQLiteDatabase db = dbHelper.getWritableDatabase();
            //dbHelper.onCreate(db);

        }catch (SQLiteException ex){
            Log.i("DB", ex.getMessage());
        }
    }

    private boolean validate() {
        boolean valid = true;
        String user_name = login_user_name.getText().toString();
        String user_password = login_user_password.getText().toString();

        if(user_name.isEmpty()){
            login_user_name.setError("Por favor ingresa tu nómina");
            valid = false;
        }else{
            login_user_name.setError(null);
        }

        if(user_password.isEmpty()){
            login_user_password.setError("Por favor ingresa tu contraseña");
            valid = false;
        }else{
            login_user_password.setError(null);
        }

        return valid;
    }

    public String getSerialNumber(){
        String serialNumber = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNumber = (String) get.invoke(c, "sys.serialnumber", "Error");
            if (serialNumber.equals("Error")) {
                serialNumber = (String) get.invoke(c, "ril.serialnumber", "Error");
            }
        }catch (Exception ex){

        }
        return serialNumber;

    }

    public String getIMEI(){
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return imei;
    }

    public class LoginSync extends AsyncTask<String, String, EntityUser>{

        private Context context;
        private ProgressDialog progressDialog;
        private String hostname = ConfigServerConnection.getHostname();
        private String port = ConfigServerConnection.getPort();
        private String pathSyncFiles = ConfigServerConnection.getPathSyncFiles();
        private LoginActionListener delegate;

        public LoginSync(Context context, LoginActionListener delegate){
            this.context = context;
            this.delegate = delegate;
        }

        @Override
        protected EntityUser doInBackground(String... params) {
            publishProgress("Validando credenciales...");
            String strUrlLogin = hostname + port + pathSyncFiles + "login.ini.php";
            try {
                String username = params[0];
                String userpassword = params[1];
                String deviceSerie = params[2];
                String devicesImei = params[3];

                EntityUser user = LoginValidate(strUrlLogin, username, userpassword, deviceSerie, devicesImei);

                if(user != null){
                    HttpHandler httpHandler = new HttpHandler();
                    publishProgress("Obteniendo usuarios");
                    List<EntityUser> usersAsignated = getUserAsignated(user);
                    usersAsignated.add(user);
                    DAO_Users dao_users = new DAO_Users(context);
                    if(dao_users.insertUsers(usersAsignated)){
                        publishProgress("Obteniendo oficinas");

                        String strUrl = hostname + port + pathSyncFiles + "getOficinas.php";
                        String jsonStrOficinas = httpHandler.makeServicesCall(strUrl);
                        List<EntityOficina> listOficinas = null;
                        if (jsonStrOficinas != null) {
                            DAO_Oficinas daoOficinas = new DAO_Oficinas(context);
                            listOficinas = SyncAuxHandlerParse.OficinasJSONParse(jsonStrOficinas);
                            if (listOficinas != null && listOficinas.size() > 0) {
                                publishProgress("Actualizando estructura de oficinas....");
                                daoOficinas.updateOficinas(listOficinas);
                                publishProgress("Se actualizo la estructura de oficinas....");
                            }
                        }
                    }
                }
                return user;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Inicio de sesión");
            progressDialog.setMessage("Conectando con el servidor");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            progressDialog.setMessage(values[0]);
        }

        @Override
        protected void onPostExecute(EntityUser entityUser) {
            // super.onPostExecute(entityUser);
            progressDialog.cancel();

            if (entityUser != null) {
                delegate.onActionListener(entityUser);
            } else {
                Toast.makeText(context, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
            }
        }



        private EntityUser LoginValidate(String strUrl, String username, String userpassword, String deviceSerie, String devicesImei) {
            EntityUser user = null;
            try {
                URL url = new URL(strUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("userpassword", "UTF-8") + "=" + URLEncoder.encode(userpassword, "UTF-8") + "&"
                        + URLEncoder.encode("devicesSerie", "UTF-8") + "=" + URLEncoder.encode(deviceSerie, "UTF-8") + "&"
                        + URLEncoder.encode("devicesImei", "UTF-8") + "=" + URLEncoder.encode(devicesImei, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();

                user = SyncAuxHandlerParse.UserLoginJsonParse(result);

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return  user;
        }

        private List<EntityUser> getUserAsignated(EntityUser user) {
            List<EntityUser> users = null;

            String strUrl = hostname + port + pathSyncFiles + "getUsers.php";

            try {
                URL url = new URL(strUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id_jefe", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(user.getId()), "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();

                users = SyncAuxHandlerParse.UserJSONParse(result);

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return  users;
        }

    }

    private void LoadNewActivity(EntityUser entityUser) {
        Intent intent = new Intent(context, PrincipalActivity.class);
        startActivity(intent);
    }

}
