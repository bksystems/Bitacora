package com.example.jurizo.bitacora.Core.BitacoraCore.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableAnswersSegment;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableDepartment;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableEmployee;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableLogs;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableOffice;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TablePositionsEmployee;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableQuestionSegment;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableRol;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableSegment;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableStatusEmployee;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableStatusUser;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableUser;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableAreas;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTablePuestos;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableSync;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableUsers;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.dbTableVisits;

/**
 * Created by jurizo on 22/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "bitacora.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db.isOpen()){
            db.execSQL(TableAnswersSegment.onCreate);
            db.execSQL(TableDepartment.onCreate);
            db.execSQL(TableEmployee.onCreate);
            db.execSQL(TableLogs.onCreate);
            db.execSQL(TableOffice.onCreate);
            db.execSQL(TablePositionsEmployee.onCreate);
            db.execSQL(TableQuestionSegment.onCreate);
            db.execSQL(TableRol.onCreate);
            db.execSQL(TableSegment.onCreate);
            db.execSQL(TableStatusEmployee.onCreate);
            db.execSQL(TableStatusUser.onCreate);
            db.execSQL(TableUser.onCreate);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dbTableAreas.OnDelete);
        db.execSQL(dbTableOficinas.OnDelete);
        db.execSQL(dbTablePuestos.OnDelete);
        db.execSQL(dbTableSync.OnDelete);
        db.execSQL(dbTableUsers.OnDelete);
        db.execSQL(dbTableVisits.OnDelete);
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
