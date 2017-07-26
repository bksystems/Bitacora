package com.example.jurizo.bitacora.Core.BitacoraCore.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableUsers;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableVisits;

/**
 * Created by jurizo on 22/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "bitacora.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db.isOpen()){
            db.execSQL(dbTableOficinas.OnCreate);
            db.execSQL(dbTableUsers.OnCreate);
            db.execSQL(dbTableVisits.OnCreate);
            insertDataTest(db);
        }
    }

    private void insertDataTest(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("fecha", "11/09/17"); // Shop Name
        values.put("user_id", 1); // Shop Phone Number
        values.put("oficina_id", 1); // Shop Phone Number
        values.put("Isupdate", 0); // Shop Phone Number
        values.put("status", 1); // Shop Phone Number
        db.insert("Visitas", null, values);

        ContentValues values1 = new ContentValues();
        values1.put("fecha", "16/09/17"); // Shop Name
        values1.put("user_id", 1); // Shop Phone Number
        values1.put("oficina_id", 6); // Shop Phone Number
        values1.put("Isupdate", 0); // Shop Phone Number
        values1.put("status", 1); // Shop Phone Number
        db.insert("Visitas", null, values1);

        ContentValues values2 = new ContentValues();
        values2.put("fecha", "23/09/17"); // Shop Name
        values2.put("user_id", 1); // Shop Phone Number
        values2.put("oficina_id", 19); // Shop Phone Number
        values2.put("Isupdate", 0); // Shop Phone Number
        values2.put("status", 0); // Shop Phone Number
        db.insert("Visitas", null, values);

        ContentValues values3 = new ContentValues();
        values3.put("nomina", 37768);
        values3.put("apellido_paterno", "Rizo");
        values3.put("apellido_materno", "Flores");
        values3.put("nombres", "Juan Carlos");
        values3.put("email", "jurizo@compartamos.com");
        values3.put("password", "empty");
        values3.put("status", 1);
        values3.put("id_jefe", 0);
        values3.put("token", "empty");
        values3.put("tokeFinish", "11/07/17");
        db.insert("Users", null, values);

        //db.close(); // Closing database connection
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dbTableOficinas.OnDelete);
        db.execSQL(dbTableUsers.OnDelete);
        db.execSQL(dbTableVisits.OnDelete);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        db.execSQL(dbTableOficinas.OnDelete);
        db.execSQL(dbTableUsers.OnDelete);
        db.execSQL(dbTableVisits.OnDelete);
        onCreate(db);
    }


}
