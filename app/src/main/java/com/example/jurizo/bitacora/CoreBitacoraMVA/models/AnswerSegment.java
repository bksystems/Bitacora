package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class AnswerSegment {
    private final int id;
    private final int questions_segment_id;
    private final String answare;
    private final int active;
    private final String created;
    private final String modified;

    public AnswerSegment(int id, int questions_segment_id, String answare, int active, String created, String modified) {
        this.id = id;
        this.questions_segment_id = questions_segment_id;
        this.answare = answare;
        this.active = active;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public int getQuestions_segment_id() {
        return questions_segment_id;
    }

    public String getAnsware() {
        return answare;
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
