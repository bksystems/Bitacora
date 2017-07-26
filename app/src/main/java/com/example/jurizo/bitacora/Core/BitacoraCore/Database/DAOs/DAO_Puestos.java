package com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DBHelper;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTablePuestos;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityPuesto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Rizo on 15/07/2017.
 */

public class DAO_Puestos {
    private Context context;
    private DBHelper helper;


    public DAO_Puestos(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public List<EntityPuesto> getPuestos() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<EntityPuesto> puestos = new ArrayList<>();
        try {
            String query = "select * from Puestos";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(0));
                String pues = cursor.getString(1);
                EntityPuesto puesto = new EntityPuesto(id, pues);
                puestos.add(puesto);
            }
        } catch (Exception ex) {
            Log.d("DB_Puesto", ex.getMessage());
        }
        return puestos;
    }

    public boolean updatePuestos(List<EntityPuesto> listPuestos) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + dbTablePuestos.TableName);
        db.execSQL(dbTablePuestos.OnCreate);
        int idPuesto = 0;
        if (db != null) {
            for (EntityPuesto os : listPuestos) {
                ContentValues values = new ContentValues();
                values.put("id", os.getId());
                values.put("puesto", os.getPuesto());
                if (db.isOpen()) {
                    idPuesto = (int) db.insert(dbTablePuestos.TableName, null, values);
                }
            }
        }
        return true;
    }

    public EntityPuesto getPuestoById(int id_puesto) {
        SQLiteDatabase db = helper.getWritableDatabase();
        EntityPuesto puesto = null;
        try {
            String query = "select * from " + dbTablePuestos.TableName + " where id = " + id_puesto;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int id = Integer.parseInt(cursor.getString(0));
                String puestoName = cursor.getString(1);
                puesto = new EntityPuesto(id, puestoName);

            }
        } catch (Exception ex) {
            Log.d("DB_Puestos", ex.getMessage());
        }
        return puesto;
    }


}
