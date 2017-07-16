package com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurizo on 29/06/17.
 */

public class DAO_Oficinas {

    private Context context;
    private DBHelper helper;


    public DAO_Oficinas(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public List<EntityOficina> getOficinas() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<EntityOficina> oficinas = new ArrayList<>();
        try {
            String query = "select * from Oficinas";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(0));
                int cc = Integer.parseInt(cursor.getString(1));
                String direccion = cursor.getString(2);
                String subdireccion = cursor.getString(3);
                String region = cursor.getString(4);
                String oficina = cursor.getString(5);
                String segmento = cursor.getString(6);
                int renovada = Integer.parseInt(cursor.getString(7));
                int cuenta_ci = Integer.parseInt(cursor.getString(8));
                String carrier = cursor.getString(9);
                EntityOficina ofi = new EntityOficina(id, cc, direccion, subdireccion, region, oficina, segmento, renovada, cuenta_ci, carrier);
                oficinas.add(ofi);
            }
        } catch (Exception ex) {
            Log.d("DB_Oficina", ex.getMessage());
        }
        return oficinas;
    }

    public boolean updateOficinas(List<EntityOficina> listOficinas) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + dbTableOficinas.TableName);
        db.execSQL(dbTableOficinas.OnCreate);

        int idOficina = 0;

        if (db != null) {
            for (EntityOficina os : listOficinas) {
                ContentValues values = new ContentValues();
                values.put("id", os.getId());
                values.put("cc", os.getCc());
                values.put("direccion", os.getDireccion());
                values.put("subdireccion", os.getSubdireccion());
                values.put("region", os.getRegion());
                values.put("oficina", os.getOficina());
                values.put("segmento", os.getSegmento());
                values.put("renovada", os.getRenovada());
                values.put("cuenta_ci", os.getCuenta_ci());
                values.put("carrier", os.getCarrier());

                if (db.isOpen()) {
                    idOficina = (int) db.insert(dbTableOficinas.TableName, null, values);
                }
            }

        }

        return true;
    }

    public EntityOficina getOficinaById(int idSearch) {
        SQLiteDatabase db = helper.getWritableDatabase();
        EntityOficina oficinaObject = null;
        try {
            String query = "select * from Oficinas where id = " + idSearch;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int id = Integer.parseInt(cursor.getString(0));
                int cc = Integer.parseInt(cursor.getString(1));
                String direccion = cursor.getString(2);
                String subdireccion = cursor.getString(3);
                String region = cursor.getString(4);
                String oficina = cursor.getString(5);
                String segmento = cursor.getString(6);
                int renovada = Integer.parseInt(cursor.getString(7));
                int cuenta_ci = Integer.parseInt(cursor.getString(8));
                String carrier = cursor.getString(9);
                oficinaObject = new EntityOficina(id, cc, direccion, subdireccion, region, oficina, segmento, renovada, cuenta_ci, carrier);

            }
        } catch (Exception ex) {
            Log.d("DB_Oficina", ex.getMessage());
        }
        return oficinaObject;
    }


}
