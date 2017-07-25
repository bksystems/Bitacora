package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Areas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Oficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Puestos;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Visits;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.SessionManagement;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableVisits;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityArea;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityPuesto;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityVisita;
import com.example.jurizo.bitacora.LoginActivity;
import com.example.jurizo.bitacora.PrincipalActivity;
import com.example.jurizo.bitacora.R;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Carlos Rizo on 26/06/2017.
 */

public class LoginSync extends AsyncTask<String, String, EntityUser>{

    private String TAG = SyncManager.class.getSimpleName();
    private Context context;
    private ProgressDialog progressDialog;

    private static String hostname = ConfigServerConnection.getHostname();
    private static String port = ConfigServerConnection.getPort();
    private static String pathSyncFiles = ConfigServerConnection.getPathSyncFiles();

    private DBHelper dbHelper;
    private AlertDialog alertDialog;

    public LoginSync(Context context){
        this.context = context;
    }

    @Override
    protected EntityUser doInBackground(String... params) {
        progressDialog.setCancelable(true);
        publishProgress("Validando usuario");
        String strUrlLogin = hostname + port + pathSyncFiles + "login.ini.php";
        try {
            String username = params[0];
            String userpassword = params[1];
            String deviceSerie = params[2];
            String devicesImei = params[3];

            EntityUser user = LoginValidate(strUrlLogin, username, userpassword, deviceSerie, devicesImei);

            if(user != null && user.getId_status() > 0){
                HttpHandler httpHandler = new HttpHandler();
                publishProgress("Descargando catalogo de oficinas");
                String jsonOficinas = httpHandler.makeServicesCall(ConfigServerConnection.getURLOficinas());
                publishProgress("Descargando catalogo de puestos");
                String jsonPuestos = httpHandler.makeServicesCall(ConfigServerConnection.getURLPuestos());
                publishProgress("Descargando catalogo de areas");
                String jsonAreas = httpHandler.makeServicesCall(ConfigServerConnection.getURLAreas());

                List<EntityOficina> oficinas = SyncAuxHandlerParse.OficinasJSONParse(jsonOficinas);
                List<EntityPuesto> puestos = SyncAuxHandlerParse.PuestosJSONParse(jsonPuestos);
                List<EntityArea> areas = SyncAuxHandlerParse.AreasJSONParse(jsonAreas);

                if(oficinas.size() > 0 && puestos.size() > 0 && areas.size() > 0){

                    DAO_Oficinas daoOficinas = new DAO_Oficinas(context);
                    DAO_Puestos daoPuestos = new DAO_Puestos(context);
                    DAO_Areas daoAreas = new DAO_Areas(context);

                    if(daoOficinas.getCount() < oficinas.size()) {
                        publishProgress("Actualizando catalogo de oficinas");
                        daoOficinas.updateOficinas(oficinas);
                    }

                    publishProgress("Actualizando catalogo de puestos");
                    daoPuestos.updatePuestos(puestos);

                    publishProgress("Actualizando catalogo de areas");
                    daoAreas.updateAreas(areas);

                    publishProgress("Actualizando información de usuarios");
                    List<EntityUser> users = new ArrayList<>();
                    users.add(user);
                    int consecutivo = 0;
                    while (consecutivo < users.size()){
                        int usrId = users.get(consecutivo).getId();
                        List<EntityUser> usrAsignated = getUserAsignated(usrId);
                        if(usrAsignated != null && usrAsignated.size() > 0) {
                            users.addAll(usrAsignated);
                        }
                        consecutivo++;
                    }
                    DAO_Users daoUsers = new DAO_Users(context);
                    daoUsers.insertUsers(users);


                    publishProgress("Descargando visitas guardas en sistema");
                    List<EntityVisita> visitas = getVisitasServer(ConfigServerConnection.getURLVisitas(), users);
                    if(visitas != null && visitas.size() > 0) {
                        publishProgress("Procesando visitas..");
                        DAO_Visits dao_visits = new DAO_Visits(context);
                        dao_visits.insertVisitas(visitas);
                    }
                }
            }

            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<EntityVisita> getVisitasServer(String strUrlVisitas, List<EntityUser> usersAsignated) {
        List<EntityVisita> visitas = null;
        String user_query = "";
        int contador = 0;
        for (EntityUser usr: usersAsignated) {
            user_query += " user_id = " + usr.getId();
            if(usersAsignated.size() > 1 && contador < usersAsignated.size() - 1){
                user_query += " or ";
            }
            contador++;
        }

        String strUrl = strUrlVisitas;

        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("users_id", "UTF-8") + "=" + URLEncoder.encode(user_query, "UTF-8");
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

            visitas = SyncAuxHandlerParse.VisitasJSONParse(result);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return  visitas;
    }

    private List<EntityUser> getUserAsignated(int userId) {
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
            String post_data = URLEncoder.encode("id_jefe", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(userId), "UTF-8");
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
        user = validateInDataBase(username, userpassword);
        if(user == null || user.getId_status() == 0 || tokenIsValid(user) == false){
            user = validateInServer(strUrl, username, userpassword, deviceSerie, devicesImei);
        }
        return  user;
    }

    private boolean tokenIsValid(EntityUser user) {
        boolean result = false;
        try {
            String token = user.getToken();
            String tokenFinish = user.getTokenFinish();
            Calendar calander = Calendar.getInstance();
            int cDay = calander.get(Calendar.DAY_OF_MONTH);
            int cMonth = calander.get(Calendar.MONTH) + 1;
            int cYear = calander.get(Calendar.YEAR);
            Date dateNow = new Date(cYear, cMonth, cDay);
            int tDay = Integer.parseInt(tokenFinish.split("-")[2]);
            int tMont = Integer.parseInt(tokenFinish.split("-")[1]);
            int tYear = Integer.parseInt(tokenFinish.split("-")[0]);
            Date dateFinish = new Date(tYear, tMont, tDay);
            if (dateFinish.after(dateNow)) {
                result = true;
            }
        }catch (Exception ex){
            result = false;
        }
        return result;
    }

    private EntityUser validateInDataBase(String username, String userpassword) {
        EntityUser user = null;
        DAO_Users dao_users = new DAO_Users(context);
        user = dao_users.loginValidate(username, userpassword);
        return user;
    }

    private EntityUser validateInServer(String strUrl, String username, String userpassword, String deviceSerie, String devicesImei) {
        EntityUser user = null;
        try {
            DAO_Visits daoVisitas = new DAO_Visits(context);
            daoVisitas.clear();

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
        return user;
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
            switch (user.getId_status()){
                case 0:
                    alertDialog.setMessage("Usuario bloqueado");
                    alertDialog.show();
                    break;
                case 1:
                    SessionManagement session = new SessionManagement(context);
                    session.createLoginSession(String.valueOf(user.getId()), String.valueOf(user.getNomina()), user.getApellido_paterno() + " " + user.getApellido_materno() + " "+ user.getNombres());
                    Intent intent = new Intent(context, PrincipalActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
