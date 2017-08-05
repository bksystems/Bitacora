package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class Session {
    private final int id;
    private final int user_id;
    private final String tocken;
    private final String finish_tocken;

    public Session(int id, int user_id, String tocken, String finish_tocken) {
        this.id = id;
        this.user_id = user_id;
        this.tocken = tocken;
        this.finish_tocken = finish_tocken;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTocken() {
        return tocken;
    }

    public String getFinish_tocken() {
        return finish_tocken;
    }
}
