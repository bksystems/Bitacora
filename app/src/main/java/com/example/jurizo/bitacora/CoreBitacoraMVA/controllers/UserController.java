package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.LogsDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.UserDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Session;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class UserController {
    private final Context context;
    private final String TAG = "C_User";
    private final String TAGClass = getClass().getName();

    public UserController(Context context) {
        this.context = context;
    }

    public User UserValidateOnLine(String username, String password, String access_type, String access_system, String ip_address, String serial_number, String imei, String sim_card_number) {
        User usr = null;
        try {
            URL url = new URL(ConfigServerConnection.URL_LoginValidate());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                    + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                    + URLEncoder.encode("access_type", "UTF-8") + "=" + URLEncoder.encode(access_type, "UTF-8") + "&"
                    + URLEncoder.encode("access_system", "UTF-8") + "=" + URLEncoder.encode(access_system, "UTF-8") + "&"
                    + URLEncoder.encode("ip_address", "UTF-8") + "=" + URLEncoder.encode(ip_address, "UTF-8") + "&"
                    + URLEncoder.encode("serial_number", "UTF-8") + "=" + URLEncoder.encode(serial_number, "UTF-8") + "&"
                    + URLEncoder.encode("imei", "UTF-8") + "=" + URLEncoder.encode(imei, "UTF-8") + "&"
                    + URLEncoder.encode("sim_card_number", "UTF-8") + "=" + URLEncoder.encode(sim_card_number, "UTF-8");
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

            usr = JSON_Parse_Office(result);

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            usr = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        } catch (IOException ex) {
            ex.printStackTrace();
            usr = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return usr;
    }


    private User JSON_Parse_Office(String JSONresult) {
        User user = null;
        Session session = null;
        try {
            JSONObject jsonObj = new JSONObject(JSONresult);
            JSONArray jsonUserData = jsonObj.getJSONArray("data");
            if (jsonUserData.length() > 0) {
                for (int i = 0; i < jsonUserData.length(); i++) {
                    JSONObject itemObj = jsonUserData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        JSONArray jsonUserUser = itemObj.getJSONArray("user");
                        for(int j = 0; j < jsonUserUser.length(); j++){
                            JSONObject itemObjUser = jsonUserUser.getJSONObject(j);
                            int user_id = itemObjUser.getInt("id");
                            int user_employe_id = itemObjUser.getInt("employee_id");
                            String user_username = itemObjUser.getString("username");
                            String user_password = itemObjUser.getString("password");
                            int user_status_user_id = itemObjUser.getInt("status_user_id");
                            int user_rol_id = itemObjUser.getInt("rol_id");
                            user = new User(user_id, user_employe_id, user_username, user_password, user_status_user_id, user_rol_id, "", "");
                        }
                        JSONArray jsonUserSession = itemObj.getJSONArray("session");
                        for(int s = 0; s < jsonUserSession.length(); s++){
                            JSONObject itemObjSession = jsonUserSession.getJSONObject(s);
                            int session_id = itemObjSession.getInt("id");
                            int session_user_id = itemObjSession.getInt("user_id");
                            String session_tocken = itemObjSession.getString("tocken");
                            String session_tocken_finish = itemObjSession.getString("finish_tocken");
                            session = new Session(session_id, session_user_id, session_tocken, session_tocken_finish);
                            if(session != null){
                                SessionController sessionController = new SessionController(context);
                                if(!sessionController.update_session_database(session)){
                                    user = null;
                                }else{
                                    UserController userController = new UserController(context);
                                    userController.update_user_database(user);
                                }
                            }
                        }
                        /*JSONArray jsonUserPermission = itemObj.getJSONArray("permissions");
                        for(int p = 0; p < jsonUserPermission.length(); p++){

                        }*/

                        /*JSONArray jsonUserEmployee = itemObj.getJSONArray("employees");
                        for (int e = 0; e < jsonUserEmployee.length(); e++){
                            JSONObject itemObjEmployee = jsonUserEmployee.getJSONObject(e);
                            int e_id = itemObjEmployee.getInt("id");
                            int e_roster = itemObjEmployee.getInt("roster");
                            String e_firs_lastname = itemObjEmployee.getString("first_lastname");
                            String e_second_lastname = itemObjEmployee.getString("second_lastanme");
                            String e_names = itemObjEmployee.getString("names");
                            String e_email = itemObjEmployee.getString("email");
                            int e_department_id = itemObjEmployee.getInt("department_id");
                            int e_position_employee_id = itemObjEmployee.getInt("e_position_employee_id");
                            int e_status_employee_id = itemObjEmployee.getInt("status_employee_id");
                            int e_employee_id = itemObjEmployee.getInt("employee_id");
                            String e_created = "";
                            String e_modified = "";
                            employee = new Employee(e_id, e_roster, e_firs_lastname, e_second_lastname, e_names, e_email, e_department_id, e_position_employee_id, e_status_employee_id, e_employee_id, e_created, e_modified);
                        }*/
                    }else{
                        user = null;
                    }
                }
            } else {
                user = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseLogin", "Json parsing error: " + e.getMessage());
        }
        return user;
    }

    private boolean update_user_database(User user) {
        UserDAO userDAO = new UserDAO(context);
        return userDAO.update_user_database(user);
    }

    public User UserValidateOffLine(String username, String password) {
        User usr = null;
        try {
            UserDAO userDAO = new UserDAO(context);
            usr = userDAO.user_validate(username, password);
        } catch (Exception ex) {
            usr = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return usr;

    }
}


