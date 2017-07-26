package com.example.jurizo.bitacora.Core.BitacoraCore.Entity;

/**
 * Created by jurizo on 30/06/17.
 */

public class EntityVisita {


    private int id;
    private String fecha;
    private EntityUser user;
    private EntityOficina oficina;
    private int isUpdate;
    private int status;


    public EntityVisita(int id, String fecha, EntityUser user, EntityOficina oficina, int isUpdate, int status) {
        this.id = id;
        this.fecha = fecha;
        this.user = user;
        this.oficina = oficina;
        this.isUpdate = isUpdate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }

    public EntityOficina getOficina() {
        return oficina;
    }

    public void setOficina(EntityOficina oficina) {
        this.oficina = oficina;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
