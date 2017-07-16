package com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableAreas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityArea;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Rizo on 15/07/2017.
 */

public class DAO_Areas {
    private Context context;
    private DBHelper helper;


    public DAO_Areas(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public List<EntityArea> getAreas() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<EntityArea> areas = new ArrayList<>();
        try {
            String query = "select * from Areas";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(0));
                String are = cursor.getString(1);
                EntityArea area = new EntityArea(id, are);
                areas.add(area);
            }
        } catch (Exception ex) {
            Log.d("DB_Atea", ex.getMessage());
        }
        return areas;
    }

    public boolean updateAreas(List<EntityArea> listAreas) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + dbTableAreas.TableName);
        db.execSQL(dbTableAreas.OnCreate);
        int idArea = 0;
        if (db != null) {
            for (EntityArea os : listAreas) {
                ContentValues values = new ContentValues();
                values.put("id", os.getId());
                values.put("area", os.getArea());
                if (db.isOpen()) {
                    idArea = (int) db.insert(dbTableAreas.TableName, null, values);
                }
            }
        }
        return true;
    }

    public EntityArea getAreaById(int id_area) {
        SQLiteDatabase db = helper.getWritableDatabase();
        EntityArea area = null;
        try {
            String query = "select * from " + dbTableAreas.TableName + " where id = " + id_area;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int id = Integer.parseInt(cursor.getString(0));
                String areaName = cursor.getString(1);
                area = new EntityArea(id, areaName);

            }
        } catch (Exception ex) {
            Log.d("DB_Areas", ex.getMessage());
        }
        return area;
    }
}
