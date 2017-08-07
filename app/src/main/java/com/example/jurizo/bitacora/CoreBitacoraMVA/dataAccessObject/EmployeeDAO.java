package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableDepartment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableEmployee;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Department;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Employee;

import java.util.List;

/**
 * Created by Carlos_Rizo on 07/08/17.
 */

public class EmployeeDAO {
    private final Context context;
    private final DBHelper helper;
    private final String TAG = "DAO_Employee";
    private final String TAGClass = getClass().getName();

    public EmployeeDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public boolean updateAll(List<Employee> employees) {
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableEmployee.TableName);
            db.execSQL(TableEmployee.onCreate);
            int count = 0;
            if (db != null) {
                for(Employee objEmployee : employees) {
                    int index = 0;
                    ContentValues values = new ContentValues();

                    values.put("id", objEmployee.getId());
                    values.put("roster", objEmployee.getRoster());
                    values.put("first_lastname", objEmployee.getFirst_lastname());
                    values.put("second_lastname", objEmployee.getSecond_lastname());
                    values.put("names", objEmployee.getNames());
                    values.put("email", objEmployee.getEmail());
                    values.put("department_id", objEmployee.getDepartment_id());
                    values.put("position_employee_id", objEmployee.getPosition_employee_id());
                    values.put("employee_id", objEmployee.getEmployee_id());
                    values.put("created", objEmployee.getCreated());
                    values.put("modified", objEmployee.getModified());
                    if (db.isOpen()) {
                        index = (int) db.insert(TableEmployee.TableName, null, values);
                        if (index > 0) {
                            count++;
                        }
                    }

                }
            }
            if(count == employees.size()){
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
