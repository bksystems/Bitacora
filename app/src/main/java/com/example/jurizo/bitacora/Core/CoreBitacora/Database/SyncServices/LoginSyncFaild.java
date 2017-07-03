package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Oficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.LoginActivity;
import com.example.jurizo.bitacora.PrincipalActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Carlos Rizo on 26/06/2017.
 */

public class LoginSyncFaild extends AsyncTask<String, String, EntityUser>{

    private String TAG = SyncManager.class.getSimpleName();
    private Context context;
    private ProgressDialog progressDialog;

    private static String hostname = ConfigServerConnection.getHostname();
    private static String port = ConfigServerConnection.getPort();
    private static String pathSyncFiles = ConfigServerConnection.getPathSyncFiles();

    private DBHelper dbHelper;
    private AlertDialog alertDialog;

    public LoginSyncFaild(Context context){
        this.context = context;
    }

    @Override
    protected EntityUser doInBackground(String... params) {
        progressDialog.setCancelable(true);
        publishProgress("Validand usuario");
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

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Inicio de sesión");
        progressDialog.setMessage("Conectando con el servidor");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(EntityUser user) {
        super.onPostExecute(user);
        progressDialog.cancel();

        if(user != null){
            switch (user.getStatus()){
                case 0:
                    alertDialog.setMessage("Usuario bloqueado");
                    alertDialog.show();
                    break;
                case 1:
                    Intent intent = new Intent(context, PrincipalActivity.class);
                    context.startActivity(intent);


            }
        }else {
            alertDialog.setMessage("Usuario o contraseña incorrecta");
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progressDialog.setMessage(values[0]);
    }
}
