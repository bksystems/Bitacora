package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class User {
    private int id;
    private int employee_id;
    private String username;
    private String password;
    private int status_user_id;
    private int rol_id;
    private String created;
    private String modified;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus_user_id() {
        return status_user_id;
    }

    public void setStatus_user_id(int status_user_id) {
        this.status_user_id = status_user_id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
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
