package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class Employee {
    private final int id;
    private final int roster;
    private final String first_lastname;
    private final String second_lastname;
    private final String names;
    private final String email;
    private final int department_id;
    private final int position_employee_id;
    private final int status_employee_id;
    private final int employee_id;
    private final String created;
    private final String modified;

    public Employee(int id, int roster, String firs_lastname, String second_lastname, String names, String email, int department_id, int position_employee_id, int status_employee_id, int employee_id, String created, String modified) {
        this.id = id;
        this.roster = roster;
        this.first_lastname = firs_lastname;
        this.second_lastname = second_lastname;
        this.names = names;
        this.email = email;
        this.department_id = department_id;
        this.position_employee_id = position_employee_id;
        this.status_employee_id = status_employee_id;
        this.employee_id = employee_id;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public int getRoster() {
        return roster;
    }

    public String getFirst_lastname() {
        return first_lastname;
    }

    public String getSecond_lastname() {
        return second_lastname;
    }

    public String getNames() {
        return names;
    }

    public String getEmail() {
        return email;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public int getPosition_employee_id() {
        return position_employee_id;
    }

    public int getStatus_employee_id() {
        return status_employee_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }
}
