package com.example.jurizo.bitacora.Core.BitacoraCore.Controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.LogsDAO;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.OfficeDAO;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices.ConfigServerConnection;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices.HttpHandler;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices.SyncAuxHandlerParse;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.BitacoraCore.Models.Office;

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
            List<Office> oficinas = SyncAuxHandlerParse.Parse_Office(jsonResponse);
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

}
