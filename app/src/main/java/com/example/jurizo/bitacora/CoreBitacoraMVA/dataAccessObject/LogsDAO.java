package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jurizo.bitacora.Controls.Utils;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableLogs;

/**
 * Created by jurizo on 26/07/17.
 */

public class LogsDAO {
    private final Context context;
    private final DBHelper helper;

    public LogsDAO(String tag, String tagClass, String message, Context context) {

        this.context = context;
        helper = new DBHelper(context);
        insertLog(tag, tagClass, message, context);
    }

    private void insertLog(String tag, String tagClass, String message, Context context) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tag", tag);
        values.put("class", tagClass);
        values.put("error", message);
        values.put("datetime", Utils.GetDateTime());
        values.put("isLoad", 0);
        if (db.isOpen()) {
            db.insert(TableLogs.TableName, null, values);
        }
    }
}
