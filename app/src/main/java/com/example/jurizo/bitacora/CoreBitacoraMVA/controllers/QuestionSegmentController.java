package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.QuestionSegmentDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.QuestionSegment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class QuestionSegmentController {
    private final String TAG = "C_QuestSeg";
    private final String TAGClass = getClass().getName();
    private final Context context;

    public QuestionSegmentController(Context context) {
        this.context = context;
    }

    public boolean Download_Update_Questions(String tocken) {
        boolean result = false;
        try {
            HttpHandler httpHandler = new HttpHandler();
            String jsonResponse = httpHandler.makeServicesCall(ConfigServerConnection.getURLQuestionSegments());
            List<QuestionSegment> questionSegment = JSON_Parse_QuestionAnswerSegments(jsonResponse);
            if(questionSegment != null && questionSegment.size() > 0){
                if(updateAllQuestionSegments(questionSegment)){
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

    private boolean updateAllQuestionSegments(List<QuestionSegment> questionSegment) {
        boolean result = false;
        if(questionSegment != null && questionSegment.size() > 0) {
            QuestionSegmentDAO questionSegmentDAO = new QuestionSegmentDAO(context);
            result = questionSegmentDAO.updateAll(questionSegment);
        }
        return result;
    }

    private List<QuestionSegment> JSON_Parse_QuestionAnswerSegments(String jsonResponse) {

        List<QuestionSegment> questionSegments = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonQuestionegmentData = jsonObj.getJSONArray("data");
            if (jsonQuestionegmentData.length() > 0) {
                for (int i = 0; i < jsonQuestionegmentData.length(); i++) {
                    JSONObject itemObj = jsonQuestionegmentData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        questionSegments = new ArrayList<>();
                        JSONArray jsonAnswerSegmentsQuestions = itemObj.getJSONArray("questions_segments");
                        for(int j = 0; j < jsonAnswerSegmentsQuestions.length(); j++){
                            JSONObject itemObjQuestionSegment = jsonAnswerSegmentsQuestions.getJSONObject(j);
                            int id = itemObjQuestionSegment.getInt("id");
                            int segment_id = itemObjQuestionSegment.getInt("segment_id");
                            String question = itemObjQuestionSegment.getString("question");
                            int active = itemObjQuestionSegment.getInt("active");
                            String created = itemObjQuestionSegment.getString("created");
                            String modified = itemObjQuestionSegment.getString("modified");
                            QuestionSegment objQuestionSegment = new QuestionSegment(id, segment_id, question, active, created, modified);
                            questionSegments.add(objQuestionSegment);
                        }

                    }else{
                        questionSegments = null;
                    }
                }
            } else {
                questionSegments = null;
            }
        } catch (final JSONException ex) {
            Log.e("ParseQSegment", "Json parsing error: " + ex.getMessage());
            LogsController.LogError(TAG, TAGClass, ex.getMessage(), context);
        }
        return questionSegments;
    }

    public List<QuestionSegment> getQuestionSegmentById(int id_segment) {
        QuestionSegmentDAO questionSegmentDAO = new QuestionSegmentDAO(context);
        return questionSegmentDAO.getQuestionById(id_segment);
    }
}
