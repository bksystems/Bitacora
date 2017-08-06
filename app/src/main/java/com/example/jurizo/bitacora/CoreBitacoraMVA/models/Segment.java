package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 06/08/17.
 */

public class Segment {
    private final int id;
    private final String department;
    private final String segment;
    private final String description;
    private final String created;
    private final String modified;

    public Segment(int id, String department, String segment, String description, String created, String modified) {
        this.id = id;
        this.department = department;
        this.segment = segment;
        this.description = description;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getSegment() {
        return segment;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }
}
