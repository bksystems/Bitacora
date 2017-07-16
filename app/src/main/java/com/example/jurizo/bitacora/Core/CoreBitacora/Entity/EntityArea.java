package com.example.jurizo.bitacora.Core.CoreBitacora.Entity;

/**
 * Created by Carlos Rizo on 15/07/2017.
 */

public class EntityArea {
    private int id;
    private String area;

    public EntityArea(int id, String area) {
        this.id = id;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
