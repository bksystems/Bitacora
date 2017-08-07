package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class QuestionSegment {
    private final int id;
    private final int segment_id;
    private final String question;
    private final int active;
    private final String created;
    private final String modified;

    public QuestionSegment(int id, int segment_id, String question, int active, String created, String modified) {
        this.id = id;
        this.segment_id = segment_id;
        this.question = question;
        this.active = active;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public int getSegment_id() {
        return segment_id;
    }

    public String getQuestion() {
        return question;
    }

    public int getActive() {
        return active;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }
}
