package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableDepartment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Department;

import java.util.List;

/**
 * Created by Carlos_Rizo on 05/08/17.
 */

public class DepartmentDAO {
    private final String TAG = "DAO_Department";
    private final String TAGClass = getClass().getName();
    private final Context context;
    private final DBHelper helper;

    public DepartmentDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public boolean updateAll(List<Department> departments) {
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableDepartment.TableName);
            db.execSQL(TableDepartment.onCreate);
            int count = 0;
            if (db != null) {
                for(Department objDepartment : departments) {
                    int index = 0;
                    ContentValues values = new ContentValues();
                    values.put("id", objDepartment.getId());
                    values.put("department", objDepartment.getDepartment());
                    values.put("description", objDepartment.getDescription());
                    values.put("created", objDepartment.getCreated());
                    values.put("modified", objDepartment.getModified());
                    if (db.isOpen()) {
                        index = (int) db.insert(TableDepartment.TableName, null, values);
                        if (index > 0) {
                            count++;
                        }
                    }

                }
            }
            if(count == departments.size()){
                result = true;
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }
}
