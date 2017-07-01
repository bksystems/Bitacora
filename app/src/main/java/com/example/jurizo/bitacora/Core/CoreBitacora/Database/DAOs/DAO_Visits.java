package com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables.dbTableOficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityUser;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityVisita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurizo on 30/06/17.
 */

public class DAO_Visits {

    private Context context;
    private DBHelper helper;


    public DAO_Visits(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public List<EntityVisita> getVisitas(){
        SQLiteDatabase db = helper.getWritableDatabase();
        List<EntityVisita> visitas = new ArrayList<>();
        try{

            DAO_Oficinas dao_oficinas = new DAO_Oficinas(context);
            DAO_Users dao_users = new DAO_Users(context);

            String query = "select * from Visitas";
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                String fecha = cursor.getString(1);
                EntityOficina oficina = dao_oficinas.getOficinaById(Integer.parseInt(cursor.getString(2)));
                EntityUser user = dao_users.getUserById(Integer.parseInt(cursor.getString(3)));
                int isupdate = Integer.parseInt(cursor.getString(4));
                int status = Integer.parseInt(cursor.getString(5));
                EntityVisita vst = new EntityVisita(id, fecha, user, oficina, isupdate, status);
                visitas.add(vst);
            }
        }catch (Exception ex){
            Log.d("DB_Visitas", ex.getMessage());
        }
        return  visitas;
    }

}
