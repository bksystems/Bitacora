package com.example.jurizo.bitacora.Core.CoreBitacora.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurizo on 22/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bitacora.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db.isOpen()){
            db.execSQL(dbTableOficinas.OnCreate);

            ContentValues values = new ContentValues();
            values.put("cc", 30001);
            values.put("direccion", "Metro");
            values.put("subdireccion", "Test");
            values.put("region", "Metro Norte");
            values.put("oficina", "Torreon");
            values.put("segmento", 1);

            // Insertar...
            db.insert(dbTableOficinas.TableName, null, values);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //----------------------Blocke para controlar todo lo relacionado a las oficinas

    public boolean updateOficinas(List<EntityOficina> listOficinas) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + dbTableOficinas.TableName);
        db.execSQL(dbTableOficinas.OnCreate);

        int idOficina = 0;

        if(db!=null){
            for (EntityOficina os : listOficinas) {
                ContentValues values = new ContentValues();
                //values.put("_id", os.getId());
                values.put("cc", os.getCc());
                values.put("direccion", os.getDireccion());
                values.put("subdireccion", os.getSubdireccion());
                values.put("region", os.getRegion());
                values.put("oficina", os.getOficina());
                values.put("segmento", os.getSegmento());
                values.put("renovada", os.getRenovada());
                values.put("cuenta_ci", os.getCuenta_ci());
                values.put("carrier", os.getCarrier());

                if(db.isOpen()) {
                    idOficina = (int) db.insert(dbTableOficinas.TableName, null, values);
                }
            }

        }

        return true;
    }

    public List<EntityOficina> getAllOficinas(){
        List<EntityOficina> oficinas = new ArrayList<>();
        try{
            String query = "select * from Oficinas";
            Cursor cursor = this.getWritableDatabase().rawQuery(query, null);
            while (cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                int cc = Integer.parseInt(cursor.getString(1));
                String direccion =cursor.getString(2);
                String subdireccion = cursor.getString(3);
                String region = cursor.getString(4);
                String oficina = cursor.getString(5);
                String segmento = cursor.getString(6);
                int renovada =  Integer.parseInt(cursor.getString(7));
                int cuenta_ci =  Integer.parseInt(cursor.getString(8));
                String carrier = cursor.getString(9);

                EntityOficina ofi = new EntityOficina(id, cc, direccion, subdireccion, region, oficina, segmento, renovada, cuenta_ci, carrier);

                oficinas.add(ofi);
            }
        }catch (Exception ex){
            Log.d("DB_Oficina", ex.getMessage());
        }
        return  oficinas;
    }

}
