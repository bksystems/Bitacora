package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.LogsDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.OfficeDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;

import org.json.JSONArray;
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
        try{
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonSuccess = jsonObj.getJSONArray("success");
            if(jsonSuccess.get(0) == "true"){

            }

            offices = new ArrayList<>();

                for (int i = 0; i < jsonSuccess.length(); i++) {

                    JSONObject osObj = jsonSuccess.getJSONObject(i);
                    int id = osObj.getInt("id");
                    int cost_center = osObj.getInt("cost_center");
                    int eco_identifier = osObj.getInt("eco_identifier");
                    String direcction = osObj.getString("direcction").trim();
                    String subdirection = osObj.getString("subdirection").trim();
                    String region = osObj.getString("region").trim();
                    String office = osObj.getString("office").trim();
                    String telephone_company = osObj.getString("telephone_company").trim();
                    boolean already_renewed = osObj.getBoolean("already_renewed");
                    int segment_logistics = osObj.getInt("segment_logistics");
                    int segment_mobility = osObj.getInt("segment_mobility");
                    int active_employees = osObj.getInt("active_employees");
                    int authorized_employees = osObj.getInt("authorized_employees");
                    int devices_assigned = osObj.getInt("devices_assigned");
                    float latitude = Float.parseFloat(osObj.getString("latitude"));
                    float longitude = Float.parseFloat(osObj.getString("longitude"));
                    Date created  = null;
                    Date modified = null;
                    Office ofi = new Office(id, cost_center, eco_identifier, direcction, subdirection, region, office,
                            telephone_company, already_renewed, segment_logistics, segment_mobility, active_employees,
                            authorized_employees, devices_assigned, latitude, longitude, created, modified);
                    offices.add(ofi);
                }
        }catch (Exception ex){
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return offices;
    }

}
