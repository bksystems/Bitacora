package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 05/08/17.
 */

public class Visit {
    private int id;
    private int employee_id;

    public Visit(int id, int employee_id) {
        this.id = id;
        this.employee_id = employee_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
}
