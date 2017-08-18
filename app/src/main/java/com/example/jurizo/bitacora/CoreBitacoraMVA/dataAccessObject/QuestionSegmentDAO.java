package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableQuestionSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableSessions;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.QuestionSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Segment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class QuestionSegmentDAO {
    private final String TAG = "DAO_QuesSegment";
    private final String TAGClass = getClass().getName();
    private final Context context;
    private final DBHelper helper;

    public QuestionSegmentDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public boolean updateAll(List<QuestionSegment> questionSegments) {
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableQuestionSegment.TableName);
            db.execSQL(TableQuestionSegment.onCreate);
            int count = 0;
            if (db != null) {
                for(QuestionSegment objQuestionSegment : questionSegments) {
                    int index = 0;
                    ContentValues values = new ContentValues();
                    values.put("id", objQuestionSegment.getId());
                    values.put("segment_id", objQuestionSegment.getSegment_id());
                    values.put("question", objQuestionSegment.getQuestion());
                    values.put("active", objQuestionSegment.getActive());
                    values.put("created", objQuestionSegment.getCreated());
                    values.put("modified", objQuestionSegment.getModified());
                    if (db.isOpen()) {
                        index = (int) db.insert(TableQuestionSegment.TableName, null, values);
                        if (index > 0) {
                            count++;
                        }
                    }

                }
            }
            if(count == questionSegments.size()){
                result = true;
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    public List<QuestionSegment> getQuestionById(int id_segment) {
        List<QuestionSegment> result = null;
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String query = "select * from " + TableQuestionSegment.TableName + " where segment_id = " + id_segment + " and active = 1 ";
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.getCount()>0) {
                result = new ArrayList<>();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    int segment_id = cursor.getInt(1);
                    String question = cursor.getString(2);
                    int active = cursor.getInt(3);
                    String created = cursor.getString(4);
                    String modified = cursor.getString(5);
                    QuestionSegment qs = new QuestionSegment(id,segment_id, question, active, created, modified);
                    result.add(qs);
                }
            }
        }catch (Exception ex){
            result = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }
}
