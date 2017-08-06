package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.DepartmentDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.LogsDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.SegmentDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Department;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Segment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class SegmentController {
    private final String TAG = "C_Segment";
    private final String TAGClass = getClass().getName();
    private final Context context;

    public SegmentController(Context context) {
        this.context = context;
    }

    public boolean Download_Update_Segments(String tocken) {
        boolean result = false;
        try {
            HttpHandler httpHandler = new HttpHandler();
            String jsonResponse = httpHandler.makeServicesCall(ConfigServerConnection.getURLSegments());
            List<Segment> departments = JSON_Parse_Segments(jsonResponse);
            if(departments != null && departments.size() > 0){
                if(updateAllSegments(departments)){
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

    private boolean updateAllSegments(List<Segment> segments) {
        boolean result = false;
        if(segments != null && segments.size() > 0) {
            SegmentDAO segmentDAO = new SegmentDAO(context);
            result = segmentDAO.updateAll(segments);
        }
        return result;
    }

    private List<Segment> JSON_Parse_Segments(String jsonResponse) {
        List<Segment> segments = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonSegmentData = jsonObj.getJSONArray("data");
            if (jsonSegmentData.length() > 0) {
                for (int i = 0; i < jsonSegmentData.length(); i++) {
                    JSONObject itemObj = jsonSegmentData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        segments = new ArrayList<>();
                        JSONArray jsonSegmentsSegment = itemObj.getJSONArray("segments");
                        for(int j = 0; j < jsonSegmentsSegment.length(); j++){
                            JSONObject itemObjSegment = jsonSegmentsSegment.getJSONObject(j);
                            int id = itemObjSegment.getInt("id");
                            String department = itemObjSegment.getString("department");
                            String segment = itemObjSegment.getString("segment");
                            String description = itemObjSegment.getString("description");
                            String created = itemObjSegment.getString("created");
                            String modified = itemObjSegment.getString("modified");
                            Segment objSegment = new Segment(id, department, segment, description, created, modified);
                            segments.add(objSegment);
                        }

                    }else{
                        segments = null;
                    }
                }
            } else {
                segments = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseSegment", "Json parsing error: " + e.getMessage());
        }
        return segments;
    }
}
