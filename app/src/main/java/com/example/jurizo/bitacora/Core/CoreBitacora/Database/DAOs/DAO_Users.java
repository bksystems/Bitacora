package com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurizo on 30/06/17.
 */

public class DAO_Users {

    private Context context;
    private DBHelper helper;


    public DAO_Users(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public List<EntityUser> getUsers(){
        SQLiteDatabase db = helper.getWritableDatabase();
        List<EntityUser> users = new ArrayList<>();
        try{
            String query = "select * from Users";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                int nomina = Integer.parseInt(cursor.getString(1));
                String apellido_paterno = cursor.getString(2);
                String apellido_materno = cursor.getString(3);
                String nombres = cursor.getString(4);
                String email = cursor.getString(5);
                String password = cursor.getString(6);
                int status = Integer.parseInt(cursor.getString(7));
                int id_jefe = Integer.parseInt(cursor.getString(8));
                String token = cursor.getString(9);
                String tokenFinish = cursor.getString(10);
                EntityUser user = new EntityUser(id, nomina, apellido_paterno, apellido_materno, nombres, email, password, status, id_jefe, token, tokenFinish);
                users.add(user);
            }
        }catch (Exception ex){
            Log.d("DB_Users", ex.getMessage());
        }
        return  users;
    }


    public EntityUser getUserById(int idSearch) {
        SQLiteDatabase db = helper.getWritableDatabase();
        EntityUser user = null;
        try{
            String query = "select * from Users where id = " + idSearch;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToFirst()){
                int id = Integer.parseInt(cursor.getString(0));
                int nomina = Integer.parseInt(cursor.getString(1));
                String apellido_paterno = cursor.getString(2);
                String apellido_materno = cursor.getString(3);
                String nombres = cursor.getString(4);
                String email = cursor.getString(5);
                String password = cursor.getString(6);
                int status = Integer.parseInt(cursor.getString(7));
                int id_jefe = Integer.parseInt(cursor.getString(8));
                String token = cursor.getString(9);
                String tokenFinish = cursor.getString(10);
                user = new EntityUser(id, nomina, apellido_paterno, apellido_materno, nombres, email, password, status, id_jefe, token, tokenFinish);

            }
        }catch (Exception ex){
            Log.d("DB_Users", ex.getMessage());
        }
        return  user;
    }
}
