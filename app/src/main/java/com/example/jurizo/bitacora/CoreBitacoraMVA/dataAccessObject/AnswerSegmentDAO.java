package com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.database.DBHelper;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables.TableAnswersSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.AnswerSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class AnswerSegmentDAO {
    private final String TAG = "DAO_AnswSeg";
    private final String TAGClass = getClass().getName();
    private final Context context;
    private final DBHelper helper;

    public AnswerSegmentDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    public boolean updateAll(List<AnswerSegment> answerSegments) {
        SQLiteDatabase db = helper.getWritableDatabase();
        boolean result = false;
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TableAnswersSegment.TableName);
            db.execSQL(TableAnswersSegment.onCreate);
            int count = 0;
            if (db != null) {
                for(AnswerSegment objAnswerSegment : answerSegments) {
                    int index = 0;
                    ContentValues values = new ContentValues();
                    values.put("id", objAnswerSegment.getId());
                    values.put("questions_segment_id", objAnswerSegment.getQuestions_segment_id());
                    values.put("answare", objAnswerSegment.getAnsware());
                    values.put("active", objAnswerSegment.getActive());
                    values.put("created", objAnswerSegment.getCreated());
                    values.put("modified", objAnswerSegment.getModified());
                    if (db.isOpen()) {
                        index = (int) db.insert(TableAnswersSegment.TableName, null, values);
                        if (index > 0) {
                            count++;
                        }
                    }

                }
            }
            if(count == answerSegments.size()){
                result = true;
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    public List<AnswerSegment> getAnswersByIdQuestion(int id_question) {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<AnswerSegment> answers = null;
        try{
            String query = "select * from " + TableAnswersSegment.TableName + " where questions_segment_id = " + id_question + " and active = 1";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() > 0) {
                answers = new ArrayList<>();
                while (cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    int question_segment_id = cursor.getInt(1);
                    String answ = cursor.getString(2);
                    int active = cursor.getInt(3);
                    String created = cursor.getString(4);
                    String modified = cursor.getString(5);
                    AnswerSegment answer = new AnswerSegment(id, question_segment_id, answ, active, created, modified);
                    answers.add(answer);
                }
            }
        }catch (Exception ex){
            answers = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return answers;
    }
}
