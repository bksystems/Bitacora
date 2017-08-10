package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableSessions;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Session;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class SessionDAO {

    private final String TAG = "DAO_Session";
    private final String TAGClass = getClass().getName();
    private final Context context;
    private final DBHelper helper;

    public SessionDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public boolean updateAllSession(Session session){
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableSessions.TableName);
            db.execSQL(TableSessions.onCreate);
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("id", session.getId());
                values.put("user_id", session.getUser_id());
                values.put("tocken", session.getTocken());
                values.put("finish_tocken", session.getFinish_tocken());

                int count = 0;
                if (db.isOpen()) {
                    count = (int) db.insert(TableSessions.TableName, null, values);
                }
                if(count > 0) {
                    result = true;
                }

            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    public Session select_by_id(int idSearch) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Session session = null;
        try {
            String query = "select * from "+ TableSessions.TableName + " where user_id = " + idSearch;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int id = Integer.parseInt(cursor.getString(0));
                int user_id = Integer.parseInt(cursor.getString(1));
                String tocken = cursor.getString(2);
                String finish_tocken = cursor.getString(3);

                session = new Session(id, user_id, tocken, finish_tocken);
            }
        } catch (Exception ex) {
            session = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return session;
    }
}
