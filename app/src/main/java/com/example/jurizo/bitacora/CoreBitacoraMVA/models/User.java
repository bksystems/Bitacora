package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class User {
    private final int id;
    private final int employee_id;
    private final String username;
    private final String password;
    private final int status_user_id;
    private final int rol_id;
    private final String created;
    private final String modified;

    public User(int id, int employee_id, String username, String password, int status_user_id, int rol_id, String created, String modified) {
        this.id = id;
        this.employee_id = employee_id;
        this.username = username;
        this.password = password;
        this.status_user_id = status_user_id;
        this.rol_id = rol_id;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getStatus_user_id() {
        return status_user_id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }
}
