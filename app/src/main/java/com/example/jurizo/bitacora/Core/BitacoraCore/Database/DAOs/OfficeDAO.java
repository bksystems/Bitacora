package com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DBHelper;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableOffice;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.BitacoraCore.Models.Office;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jurizo on 26/07/17.
 */

public class OfficeDAO {
    private final Context context;
    private final DBHelper helper;
    private final String TAG = "OfficesDAO";
    private final String TAGClass = getClass().getName();


    public OfficeDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }


    public List<Office> getOffices() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<Office> offices = new ArrayList<>();
        try{
            String query = "select * from " + TableOffice.TableName;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                Office officeObj = CreateParseObject(cursor);
                offices.add(officeObj);
            }
        }catch (Exception ex){
            offices = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return offices;
    }

    private Office CreateParseObject(Cursor cursor) {
        Office officeObj = null;
        try{
            int id = Integer.parseInt(cursor.getString(0));
            int cost_center = Integer.parseInt(cursor.getString(1));
            int eco_identifier = Integer.parseInt(cursor.getString(2));
            String direcction = cursor.getString(3);
            String subdirection = cursor.getString(4);
            String region = cursor.getString(5);
            String office = cursor.getString(6);
            String telephone_company = cursor.getString(7);
            boolean already_renewed = Boolean.parseBoolean(cursor.getString(8));
            int segment_logistics = Integer.parseInt(cursor.getString(9));
            int segment_mobility = Integer.parseInt(cursor.getString(10));
            int active_employees = Integer.parseInt(cursor.getString(11));
            int authorized_employees = Integer.parseInt(cursor.getString(12));
            int devices_assigned = Integer.parseInt(cursor.getString(13));
            float latitude = Float.parseFloat(cursor.getString(14));
            float longitude = Float.parseFloat(cursor.getString(15));
            Date created  = null;
            Date modified = null;
            officeObj = new Office(id, cost_center, eco_identifier, direcction, subdirection, region, office, telephone_company
                    , already_renewed, segment_logistics, segment_mobility, active_employees, authorized_employees, devices_assigned, latitude, longitude,
                    created, modified);
        }catch (Exception ex){
            officeObj = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return  officeObj;
    }

    public Office getOfficeById(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Office ofcObj = null;
        try {
            String query = "select * from " + TableOffice.TableName + " where id = " + id;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                ofcObj = CreateParseObject(cursor);
            }
        }catch (Exception ex){
            ofcObj = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return ofcObj;
    }

    public Office getOfficeCCId(int cc) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Office ofcObj = null;
        try {
            String query = "select * from " + TableOffice.TableName + " where cost_center = " + cc;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                ofcObj = CreateParseObject(cursor);
            }
        }catch (Exception ex){
            ofcObj = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return ofcObj;
    }

    public Boolean updateAllOffices(List<Office> offices) {
        boolean result = false;
        int contador = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.execSQL("DROP TABLE IF EXISTS " + dbTableOficinas.TableName);
            db.execSQL(dbTableOficinas.OnCreate);

            int idOficina = 0;

            if (db != null) {
                for (Office os : offices) {
                    ContentValues values = getContentValues(os);
                    if (values != null) {
                        if (db.isOpen()) {
                            idOficina = (int) db.insert(dbTableOficinas.TableName, null, values);
                            if(idOficina > 0){
                                contador++;
                            }
                        }
                    }
                }
                if(contador == offices.size()){
                    result = true;
                }
            }
        }catch (Exception ex){
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    private ContentValues getContentValues(Office os) {
        ContentValues values = null;
        try{
            values = new ContentValues();
            values.put("id", os.getId());
            values.put("cost_center", os.getCost_center());
            values.put("eco_identifier", os.getEco_identifier());
            values.put("direcction", os.getDirecction());
            values.put("subdirection", os.getSubdirection());
            values.put("region", os.getRegion());
            values.put("office", os.getOffice());
            values.put("telephone_company", os.getTelephone_company());
            values.put("already_renewed", os.isAlready_renewed());
            values.put("segment_logistics", os.getSegment_logistics());
            values.put("segment_mobility", os.getSegment_mobility());
            values.put("active_employees", os.getActive_employees());
            values.put("authorized_employees", os.getAuthorized_employees());
            values.put("devices_assigned", os.getDevices_assigned());
            values.put("latitude", os.getLatitude());
            values.put("longitude", os.getLongitude());
            values.put("created", "null");
            values.put("modified", "null");
        }catch (Exception ex){
            values = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return values;
    }

    public int getCount() {
        try {
            String countQuery = "SELECT  * FROM " + TableOffice.TableName;
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int cnt = cursor.getCount();
            cursor.close();
            return cnt;
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
            return 0;
        }
    }
}
