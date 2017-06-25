package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
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

            JSONArray oficinasArray = jsonObj.getJSONArray("oficinas");

            oficinas = new ArrayList<>();

            for (int i = 0; i < oficinasArray.length(); i++) {

                JSONObject oficinaObject = oficinasArray.getJSONObject(i);

                JSONObject osObj = oficinaObject.getJSONObject("oficina");

                Integer id = osObj.getInt("id");
                Integer cc = osObj.getInt("cc");
                String direccion = osObj.getString("direccion").trim();
                String subdireccion = osObj.getString("subdireccion").trim();
                String region = osObj.getString("region").trim();
                String os = osObj.getString("oficina").trim();
                String segmento = osObj.getString("segmento").trim();

                EntityOficina oficina = new EntityOficina(id, cc, direccion, subdireccion, region, os, segmento);

                oficinas.add(oficina);

            }
        } catch (final JSONException e) {
            Log.e("ParseOficina", "Json parsing error: " + e.getMessage());
        }
        return oficinas;
    }
}
