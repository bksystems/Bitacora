package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Areas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Puestos;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Users;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityArea;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityPuesto;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityVisita;
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
                int plantilla_autorizada = osObj.getInt("plantilla_autorizada");
                int plantilla_ventas = osObj.getInt("plantilla_ventas");
                int inventario_dm = osObj.getInt("inventario_dm");
                float latitud = Float.parseFloat(osObj.getString("latitud"));
                float longitud = Float.parseFloat(osObj.getString("longitud"));

                EntityOficina oficina = new EntityOficina(id, cc, direccion, subdireccion, region, os, segmento,
                        renovada, cuenta_ci, carrier, plantilla_autorizada, plantilla_ventas, inventario_dm, latitud, longitud);
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
                    int id_status = osObj.getInt("id_status");
                    int id_jefe = osObj.getInt("id_jefe");
                    int id_puesto = osObj.getInt("id_puesto");
                    int id_area = osObj.getInt("id_area");
                    String token = osObj.getString("token").trim();
                    String tokenFinish = osObj.getString("finishToken").trim();
                    EntityPuesto puesto = new EntityPuesto(id_puesto, "");
                    EntityArea area = new EntityArea(id_area, "");
                    user = new EntityUser(id, nomina, apellido_paterno, apellido_materno, nombres, email, password, id_status, id_jefe, puesto, area, token, tokenFinish);
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
                    int id_status = osObj.getInt("id_status");
                    int id_jefe = osObj.getInt("id_jefe");
                    int id_puesto = osObj.getInt("id_puesto");
                    int id_area = osObj.getInt("id_area");
                    EntityPuesto puesto = new EntityPuesto(id_puesto, "");
                    EntityArea area = new EntityArea(id_area, "");
                    String token = "";
                    String finishToken = "";

                    EntityUser user = new EntityUser(id, nomina, apellido_paterno, apellido_materno, nombres, email, password, id_status, id_jefe, puesto, area, token, finishToken);
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

    public static List<EntityVisita> VisitasJSONParse(String strjson) {
        List<EntityVisita> visitas = null;

        try {
            JSONObject jsonLoginResult = new JSONObject(strjson);

            JSONArray jsonUserLogin = jsonLoginResult.getJSONArray("data");

            if (jsonUserLogin.length() > 0) {
                visitas = new ArrayList<>();
                for (int i = 0; i < jsonUserLogin.length(); i++) {

                    JSONObject osObj = jsonUserLogin.getJSONObject(i);
                    Integer id = osObj.getInt("id");
                    String fehca = osObj.getString("fecha").trim();
                    Integer user_id = osObj.getInt("user_id");
                    Integer oficina_id = osObj.getInt("oficina_id");
                    Integer isupdate = osObj.getInt("isupdate");
                    Integer status = osObj.getInt("status");
                    EntityPuesto puesto = new EntityPuesto(0, "");
                    EntityArea area = new EntityArea(0, "");
                    EntityUser usr = new EntityUser(user_id, 0, "", "", "", "", "", 0, 0,puesto,area, "", "");
                    EntityOficina os = new EntityOficina(oficina_id, 0, "", "", "", "", "", 0,0,"",0,0,0,0,0);
                    EntityVisita visita = new EntityVisita(id, fehca, usr, os, isupdate, status);
                    visitas.add(visita);
                }
            } else {
                visitas = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseVisita", "Json parsing error: " + e.getMessage());
        }
        return visitas;
    }

    public static List<EntityArea> AreasJSONParse(String json){
        List<EntityArea> areas = null;
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (jsonArray.length() > 0) {
                areas = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject areaJSONObj = jsonArray.getJSONObject(i);
                    Integer id = areaJSONObj.getInt("id");
                    String area = areaJSONObj.getString("area").trim();
                    EntityArea areaEntity = new EntityArea(id, area);
                    areas.add(areaEntity);
                }
            }
        } catch (final JSONException e) {
            Log.e("AreaParse", "Json parsing error: " + e.getMessage());
        }
        return areas;
    }

    public static List<EntityPuesto> PuestosJSONParse(String json){
        List<EntityPuesto> puestos = null;
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (jsonArray.length() > 0) {
                puestos = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject areaJSONObj = jsonArray.getJSONObject(i);
                    Integer id = areaJSONObj.getInt("id");
                    String puesto = areaJSONObj.getString("puesto").trim();
                    EntityPuesto puestoEntity = new EntityPuesto(id, puesto);
                    puestos.add(puestoEntity);
                }
            }
        } catch (final JSONException e) {
            Log.e("PuestoParse", "Json parsing error: " + e.getMessage());
        }
        return puestos;
    }
}
