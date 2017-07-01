package com.example.jurizo.bitacora.Core.CoreBitacora.Entity;

/**
 * Created by jurizo on 30/06/17.
 */

public class EntityUser {

    private int id;
    private int nomina;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String email;
    private String password;
    private int status;
    private int id_jefe;
    private String token;
    private String tokenFinish;

    public EntityUser(int id, int nomina, String apellido_paterno, String apellido_materno, String nombres, String email, String password, int status, int id_jefe, String token, String tokenFinish) {
        this.id = id;
        this.nomina = nomina;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombres = nombres;
        this.email = email;
        this.password = password;
        this.status = status;
        this.id_jefe = id_jefe;
        this.token = token;
        this.tokenFinish = tokenFinish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNomina() {
        return nomina;
    }

    public void setNomina(int nomina) {
        this.nomina = nomina;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId_jefe() {
        return id_jefe;
    }

    public void setId_jefe(int id_jefe) {
        this.id_jefe = id_jefe;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenFinish() {
        return tokenFinish;
    }

    public void setTokenFinish(String tokenFinish) {
        this.tokenFinish = tokenFinish;
    }
}
