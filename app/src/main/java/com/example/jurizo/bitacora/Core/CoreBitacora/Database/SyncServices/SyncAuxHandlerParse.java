package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.SysDbVersionEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Rizo on 25/06/2017.
 */

public class SyncAuxHandlerParse {

    public static SysDbVersionEntity DBVersionParse(String jsonStrVersionDB) {
        SysDbVersionEntity objVersion = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonStrVersionDB);
            JSONArray arrayVersionJSON = jsonObj.getJSONArray("versions");

            JSONObject objJsonVersion = arrayVersionJSON.getJSONObject(0);
            JSONObject verObj = objJsonVersion.getJSONObject("version");
            Integer id = verObj.getInt("id");
            String date = verObj.getString("date");
            int updateOficinas = verObj.getInt("updateOficinas");

            objVersion = new SysDbVersionEntity(id, date, updateOficinas);

        } catch (final JSONException e) {
            Log.e("ParseVer", "Json parsing error: " + e.getMessage());
        }
        return objVersion;
    }

    public static List<EntityOficina> OficinasJSONParse(String jsonStrOficinas) {
        List<EntityOficina> oficinas = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonStrOficinas);

            JSONArray oficinasArray = jsonObj.getJSONArray("data");

            oficinas = new ArrayList<>();

            for (int i = 0; i < oficinasArray.length(); i++) {

                JSONObject osObj = oficinasArray.getJSONObject(i);
                Integer id = osObj.getInt("id");
                Integer cc = osObj.getInt("cc");
                String direccion = osObj.getString("direccion").trim();
                String subdireccion = osObj.getString("subdireccion").trim();
                String region = osObj.getString("region").trim();
                String os = osObj.getString("oficina").trim();
                String segmento = osObj.getString("segmento").trim();
                int renovada = osObj.getInt("renovada");
                int cuenta_ci = osObj.getInt("cuenta_ci");
                String carrier = osObj.getString("carrier").trim();

                EntityOficina oficina = new EntityOficina(id, cc, direccion, subdireccion, region, os, segmento, renovada, cuenta_ci, carrier);

                oficinas.add(oficina);

            }
        } catch (final JSONException e) {
            Log.e("ParseOficina", "Json parsing error: " + e.getMessage());
        }
        return oficinas;
    }
    

    public static EntityUser UserLoginJsonParse(String strjsonLoginResult) {
        EntityUser user = null;
        try {
            JSONObject jsonLoginResult = new JSONObject(strjsonLoginResult);

            JSONArray jsonUserLogin = jsonLoginResult.getJSONArray("data");

            if (jsonUserLogin.length() > 0) {
                for (int i = 0; i < jsonUserLogin.length(); i++) {

                    JSONObject osObj = jsonUserLogin.getJSONObject(i);
                    Integer id = osObj.getInt("id");
                    Integer nomina = osObj.getInt("nomina");
                    String apellido_paterno = osObj.getString("apellido_paterno").trim();
                    String apellido_materno = osObj.getString("apellido_materno").trim();
                    String nombres = osObj.getString("nombres").trim();
                    String email = osObj.getString("email").trim();
                    String password = osObj.getString("password").trim();
                    int status = osObj.getInt("status");
                    int id_jefe = osObj.getInt("id_jefe");
                    String token = osObj.getString("token").trim();
                    String finishToken = osObj.getString("finishToken").trim();

                    user = new EntityUser(id, nomina, apellido_paterno, apellido_materno, nombres, email, password, status, id_jefe, token, finishToken);
                }
            } else {
                user = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseLogin", "Json parsing error: " + e.getMessage());
        }
        return user;
    }

    public static List<EntityUser> UserJSONParse(String strjsonuser) {
        List<EntityUser> users = null;

        try {
            JSONObject jsonLoginResult = new JSONObject(strjsonuser);

            JSONArray jsonUserLogin = jsonLoginResult.getJSONArray("data");

            if (jsonUserLogin.length() > 0) {
                users = new ArrayList<>();
                for (int i = 0; i < jsonUserLogin.length(); i++) {

                    JSONObject osObj = jsonUserLogin.getJSONObject(i);
                    Integer id = osObj.getInt("id");
                    Integer nomina = osObj.getInt("nomina");
                    String apellido_paterno = osObj.getString("apellido_paterno").trim();
                    String apellido_materno = osObj.getString("apellido_materno").trim();
                    String nombres = osObj.getString("nombres").trim();
                    String email = osObj.getString("email").trim();
                    String password = osObj.getString("password").trim();
                    int status = osObj.getInt("status");
                    int id_jefe = osObj.getInt("id_jefe");
                    String token = "";
                    String finishToken = "";

                    EntityUser user = new EntityUser(id, nomina, apellido_paterno, apellido_materno, nombres, email, password, status, id_jefe, token, finishToken);
                    users.add(user);
                }
            } else {
                users = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseUsers", "Json parsing error: " + e.getMessage());
        }
        return users;
    }
}
