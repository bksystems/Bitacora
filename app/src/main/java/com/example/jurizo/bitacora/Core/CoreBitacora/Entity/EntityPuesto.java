package com.example.jurizo.bitacora.Core.CoreBitacora.Entity;

/**
 * Created by Carlos Rizo on 15/07/2017.
 */

public class EntityPuesto {
    private int id;
    private String puesto;

    public EntityPuesto(int id, String puesto) {
        this.id = id;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
