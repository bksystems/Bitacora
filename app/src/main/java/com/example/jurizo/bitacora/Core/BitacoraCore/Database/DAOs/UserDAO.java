package com.example.jurizo.bitacora.Core.BitacoraCore.Database.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.BitacoraCore.Database.DBHelper;
import com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables.TableUser;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityArea;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityPuesto;
import com.example.jurizo.bitacora.Core.BitacoraCore.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.BitacoraCore.Models.User;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class UserDAO {
    private final String TAG = "UserDAO";
    private final String TAGClass = getClass().getName();

    private final Context context;
    private final DBHelper helper;

    public UserDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public User user_validate(String username, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        User user = null;
        try {
            String query = "select * from users where username = " + username + " and password like '" + password + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                int id = Integer.parseInt(cursor.getString(0));
                int employee_id = Integer.parseInt(cursor.getString(1));
                String username_d = cursor.getString(2);
                String password_d = cursor.getString(3);
                int status_user_id = Integer.parseInt(cursor.getString(4));
                int rol_id = Integer.parseInt(cursor.getString(5));
                String created = cursor.getString(6);
                String modified = cursor.getString(7);

                user = new User(id, employee_id, username_d, password_d, status_user_id, rol_id, created, modified);
            }
        } catch (Exception ex) {
            user = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return user;
    }

    public boolean update_user_database(User user) {
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableUser.TableName);
            db.execSQL(TableUser.onCreate);
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("id", user.getId());
                values.put("employee_id", user.getEmployee_id());
                values.put("username", user.getUsername());
                values.put("password", user.getPassword());
                values.put("status_user_id", user.getStatus_user_id());
                values.put("rol_id", user.getRol_id());
                values.put("created", user.getCreated());
                values.put("modified", user.getModified());

                int count = 0;
                if (db.isOpen()) {
                    count = (int) db.insert(TableUser.TableName, null, values);
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
}
