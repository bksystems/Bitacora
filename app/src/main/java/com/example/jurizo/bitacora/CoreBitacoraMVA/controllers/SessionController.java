package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessLayer.SessionDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Session;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class SessionController {
    private final Context context;
    private final String TAG = "C_Session";
    private final String TAGClass = getClass().getName();

    public SessionController (Context context){
        this.context = context;
    }

    public boolean update_session_database(Session session) {
        SessionDAO sessionDAO = new SessionDAO(context);
        return sessionDAO.updateAllSession(session);
    }

    public Session get_Final_Session(int id) {
        SessionDAO sessionDAO = new SessionDAO(context);
        return sessionDAO.select_by_id(id);
    }
}
