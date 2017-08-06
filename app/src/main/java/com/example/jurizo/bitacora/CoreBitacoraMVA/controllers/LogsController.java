package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.LogsDAO;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class LogsController {
    public static void LogError(String tag, String TAGClass, String message, Context context) {
        LogsDAO logsDAO = new LogsDAO(tag, TAGClass, message, context);
    }
}
