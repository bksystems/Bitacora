package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.LogsDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.OfficeDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Segment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jurizo on 26/07/17.
 */

public class OfficesController {
    private final String TAG = "C_Offices";
    private final String TAGClass = getClass().getName();

    private Context context;

    public  OfficesController(Context context){
        this.context = context;
    }

    public List<Office> getOfficesDataBase(){
        List<Office> offices = null;
        try {
            OfficeDAO dao = new OfficeDAO(context);
            offices = dao.getOffices();
        }catch (Exception ex){
            offices = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return offices;
    }

    public Office getOfficeDataBaseById(int id){
        Office office = null;
        try {
            OfficeDAO dao = new OfficeDAO(context);
            office = dao.getOfficeById(id);
        }catch (Exception ex){
            office = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return office;
    }

    public Boolean updateAllOffices(List<Office> offices){
        Boolean result = false;
        try {
            OfficeDAO dao = new OfficeDAO(context);
            result = dao.updateAllOffices(offices);
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    public Boolean Download_Update_Offices(String tocken){
        boolean result = false;
        try {
            HttpHandler httpHandler = new HttpHandler();
            String jsonResponse = httpHandler.makeServicesCall(ConfigServerConnection.getURLOffices());
            List<Office> oficinas = JSON_Parse_Office(jsonResponse);
            if(oficinas != null && oficinas.size() > 0){
                if(updateAllOffices(oficinas)){
                    result = true;
                }
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }


    private List<Office> JSON_Parse_Office(String jsonResponse) {
        List<Office> offices = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonOfficesData = jsonObj.getJSONArray("data");
            if (jsonOfficesData.length() > 0) {
                for (int i = 0; i < jsonOfficesData.length(); i++) {
                    JSONObject itemObj = jsonOfficesData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        offices = new ArrayList<>();
                        JSONArray jsonOfficesOffice = itemObj.getJSONArray("offices");
                        for(int j = 0; j < jsonOfficesOffice.length(); j++){
                            JSONObject itemObjOffice = jsonOfficesOffice.getJSONObject(j);
                            int id = itemObjOffice.getInt("id");
                            int cost_center = itemObjOffice.getInt("cost_center");
                            int eco_identifier = itemObjOffice.getInt("eco_identifier");
                            String direcction = itemObjOffice.getString("direcction").trim();
                            String subdirection = itemObjOffice.getString("subdirection").trim();
                            String region = itemObjOffice.getString("region").trim();
                            String office = itemObjOffice.getString("office").trim();
                            String telephone_company = itemObjOffice.getString("telephone_company").trim();
                            int already_renewed = itemObjOffice.getInt("already_renewed");
                            int segment_logistics = itemObjOffice.getInt("segment_logistics");
                            int segment_mobility = itemObjOffice.getInt("segment_mobility");
                            int active_employees = itemObjOffice.getInt("active_employees");
                            int authorized_employees = itemObjOffice.getInt("authorized_employees");
                            int devices_assigned = itemObjOffice.getInt("devices_assigned");
                            float latitude = Float.parseFloat(itemObjOffice.getString("latitude"));
                            float longitude = Float.parseFloat(itemObjOffice.getString("longitude"));
                            Date created  = null;
                            Date modified = null;
                            Office ofi = new Office(id, cost_center, eco_identifier, direcction, subdirection, region, office,
                                    telephone_company, already_renewed, segment_logistics, segment_mobility, active_employees,
                                    authorized_employees, devices_assigned, latitude, longitude, created, modified);
                            offices.add(ofi);
                        }

                    }else{
                        offices = null;
                    }
                }
            } else {
                offices = null;
            }
        } catch (final JSONException ex) {
            Log.e("ParseOffices", "Json parsing error: " + ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return offices;
    }

}
