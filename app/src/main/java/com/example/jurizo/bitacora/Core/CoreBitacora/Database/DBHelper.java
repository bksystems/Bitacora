package com.example.jurizo.bitacora.Core.CoreBitacora.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableUsers;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableVisits;
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
            db.execSQL(dbTableUsers.OnCreate);
            db.execSQL(dbTableVisits.OnCreate);
        }
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
