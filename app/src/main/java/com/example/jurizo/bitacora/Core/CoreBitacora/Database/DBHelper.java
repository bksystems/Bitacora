package com.example.jurizo.bitacora.Core.CoreBitacora.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jurizo on 22/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bitacora.db";

    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
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
}
