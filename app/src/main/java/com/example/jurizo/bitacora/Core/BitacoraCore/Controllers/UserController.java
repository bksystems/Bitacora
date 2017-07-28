package com.example.jurizo.bitacora.Core.BitacoraCore.Controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.LogsDAO;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.UserDAO;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices.ConfigServerConnection;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices.SyncAuxHandlerParse;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityArea;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityPuesto;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.BitacoraCore.Models.User;

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
        try {
            JSONObject jsonObje = new JSONObject(JSONresult);

            JSONArray jsonUserLogin = jsonObje.getJSONArray("user");

            if (jsonUserLogin.length() > 0) {



            } else {
                user = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseLogin", "Json parsing error: " + e.getMessage());
        }
        return user;
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


