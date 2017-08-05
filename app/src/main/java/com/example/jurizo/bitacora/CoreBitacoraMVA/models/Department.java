package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 05/08/17.
 */

public class Department {
    private int id;
    private String department;
    private String description;
    private String created;
    private String modified;

    public Department(int id, String department, String description, String created, String modified){
        this.id = id;
        this.department = department;
        this.description = description;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
