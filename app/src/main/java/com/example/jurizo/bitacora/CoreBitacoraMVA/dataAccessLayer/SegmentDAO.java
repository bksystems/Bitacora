package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Segment;

import java.util.List;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class SegmentDAO {
    private final String TAG = "DAO_Segment";
    private final String TAGClass = getClass().getName();
    private final Context context;
    private final DBHelper helper;

    public SegmentDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public boolean updateAll(List<Segment> segments) {
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableSegment.TableName);
            db.execSQL(TableSegment.onCreate);
            int count = 0;
            if (db != null) {
                for(Segment objSegment : segments) {
                    int index = 0;
                    ContentValues values = new ContentValues();
                    values.put("id", objSegment.getId());
                    values.put("department", objSegment.getDepartment());
                    values.put("segment", objSegment.getSegment());
                    values.put("description", objSegment.getDescription());
                    values.put("created", objSegment.getCreated());
                    values.put("modified", objSegment.getModified());
                    if (db.isOpen()) {
                        index = (int) db.insert(TableSegment.TableName, null, values);
                        if (index > 0) {
                            count++;
                        }
                    }

                }
            }
            if(count == segments.size()){
                result = true;
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }
}
