package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.DepartmentDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.LogsDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Department;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class DepartmentController {
    private final Context context;
    private final String TAG ="C_Departments";
    private final String TAGClass = getClass().getName();

    public DepartmentController(Context context) {
        this.context = context;
    }

    public boolean Download_Update_Department(String tocken) {
        boolean result = false;
        try {
            HttpHandler httpHandler = new HttpHandler();
            String jsonResponse = httpHandler.makeServicesCall(ConfigServerConnection.getURLDepartmanets());
            List<Department> departments = JSON_Parse_Department(jsonResponse);
            if(departments != null && departments.size() > 0){
                if(updateAllDepartments(departments)){
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

    private boolean updateAllDepartments(List<Department> departments) {
        boolean result = false;
        if(departments != null && departments.size() > 0) {
            DepartmentDAO departmenDAO = new DepartmentDAO(context);
            result = departmenDAO.updateAll(departments);
        }
        return result;
    }


    private List<Department> JSON_Parse_Department(String jsonResponse) {
        List<Department> departments = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonUDepartmentData = jsonObj.getJSONArray("data");
            if (jsonUDepartmentData.length() > 0) {
                for (int i = 0; i < jsonUDepartmentData.length(); i++) {
                    JSONObject itemObj = jsonUDepartmentData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        departments = new ArrayList<>();
                        JSONArray jsonDepartmentDeprtment = itemObj.getJSONArray("departments");
                        for(int j = 0; j < jsonDepartmentDeprtment.length(); j++){
                            JSONObject itemObjUser = jsonDepartmentDeprtment.getJSONObject(j);
                            int id = itemObjUser.getInt("id");
                            String department = itemObjUser.getString("department");
                            String description = itemObjUser.getString("description");
                            String created = itemObjUser.getString("created");
                            String modified = itemObjUser.getString("modified");
                            Department objDepartment = new Department(id, department, description, created, modified);
                            departments.add(objDepartment);
                        }

                    }else{
                        departments = null;
                    }
                }
            } else {
                departments = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseDepartment", "Json parsing error: " + e.getMessage());
        }
        return departments;
    }
}
