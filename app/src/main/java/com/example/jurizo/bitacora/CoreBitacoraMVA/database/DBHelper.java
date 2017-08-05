package com.example.jurizo.bitacora.CoreBitacoraMVA.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableAnswersSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableDepartment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableEmployee;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableLogs;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableOffice;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TablePositionsEmployee;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableQuestionSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableRol;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableSessions;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableStatusEmployee;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableStatusUser;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableUser;

/**
 * Created by jurizo on 22/06/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 8;
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
            db.execSQL(TableSessions.onCreate);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableAnswersSegment.onDelete);
        db.execSQL(TableDepartment.onDelete);
        db.execSQL(TableEmployee.onDelete);
        db.execSQL(TableLogs.onDelete);
        db.execSQL(TableOffice.onDelete);
        db.execSQL(TablePositionsEmployee.onDelete);
        db.execSQL(TableQuestionSegment.onDelete);
        db.execSQL(TableRol.onDelete);
        db.execSQL(TableSegment.onDelete);
        db.execSQL(TableStatusEmployee.onDelete);
        db.execSQL(TableStatusUser.onDelete);
        db.execSQL(TableUser.onDelete);
        db.execSQL(TableSessions.onDelete);

        onCreate(db);
    }

}
