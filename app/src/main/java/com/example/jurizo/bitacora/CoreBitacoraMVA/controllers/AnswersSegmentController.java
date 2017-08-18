package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.AnswerSegmentDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.AnswerSegment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class AnswersSegmentController {
    private final String TAG = "C_AnswerSeg";
    private final String TAGClass = getClass().getName();
    private final Context context;

    public AnswersSegmentController(Context context) {
        this.context = context;
    }

    public boolean Download_Update_Answers(String tocken) {
        boolean result = false;
        try {
            HttpHandler httpHandler = new HttpHandler();
            String jsonResponse = httpHandler.makeServicesCall(ConfigServerConnection.getURLAnswareSegments());
            List<AnswerSegment> answerSegments = JSON_Parse_AnswerSegments(jsonResponse);
            if(answerSegments != null && answerSegments.size() > 0){
                if(updateAllAnswerSegments(answerSegments)){
                    result = true;
                }
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsController.LogError(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    private boolean updateAllAnswerSegments(List<AnswerSegment> answerSegments) {
        boolean result = false;
        if(answerSegments != null && answerSegments.size() > 0) {
            AnswerSegmentDAO answerSegmentDAO = new AnswerSegmentDAO(context);
            result = answerSegmentDAO.updateAll(answerSegments);
        }
        return result;
    }

    private List<AnswerSegment> JSON_Parse_AnswerSegments(String jsonResponse) {
        List<AnswerSegment> answerSegments = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonAnswerSegmentData = jsonObj.getJSONArray("data");
            if (jsonAnswerSegmentData.length() > 0) {
                for (int i = 0; i < jsonAnswerSegmentData.length(); i++) {
                    JSONObject itemObj = jsonAnswerSegmentData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        answerSegments = new ArrayList<>();
                        JSONArray jsonAnswerSegmentsQuestions = itemObj.getJSONArray("answers_segments");
                        for(int j = 0; j < jsonAnswerSegmentsQuestions.length(); j++){
                            JSONObject itemObjAnswerSegment = jsonAnswerSegmentsQuestions.getJSONObject(j);
                            int id = itemObjAnswerSegment.getInt("id");
                            int questions_segment_id = itemObjAnswerSegment.getInt("questions_segment_id");
                            String answare = itemObjAnswerSegment.getString("answare");
                            int active = itemObjAnswerSegment.getInt("active");
                            String created = itemObjAnswerSegment.getString("created");
                            String modified = itemObjAnswerSegment.getString("modified");
                            AnswerSegment objAnswerSegment = new AnswerSegment(id, questions_segment_id, answare, active, created, modified);
                            answerSegments.add(objAnswerSegment);

                        }

                    }else{
                        answerSegments = null;
                    }
                }
            } else {
                answerSegments = null;
            }
        } catch (final JSONException ex) {
            Log.e("ParseSegment", "Json parsing error: " + ex.getMessage());
            LogsController.LogError(TAG, TAGClass, ex.getMessage(), context);
        }
        return answerSegments;
    }

    public List<AnswerSegment> getAnswersQuestionsByIdQuestion(int id_question) {
        AnswerSegmentDAO answerSegmentDAO = new AnswerSegmentDAO(context);
        return  answerSegmentDAO.getAnswersByIdQuestion(id_question);
    }
}
