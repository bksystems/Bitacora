package com.example.jurizo.bitacora.Core.BitacoraCore.Controllers;

import android.content.Context;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs.OfficeDAO;
import com.example.jurizo.bitacora.Core.BitacoraCore.Models.Office;

import java.util.List;

/**
 * Created by jurizo on 26/07/17.
 */

public class OfficesController {
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
        }
        return result;
    }

}
