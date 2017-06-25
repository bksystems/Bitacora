package com.example.jurizo.bitacora.Core.CoreBitacora.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;

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

                if(db.isOpen()) {
                    idOficina = (int) db.insert(dbTableOficinas.TableName, null, values);
                }
            }

        }

        return true;
    }
}
