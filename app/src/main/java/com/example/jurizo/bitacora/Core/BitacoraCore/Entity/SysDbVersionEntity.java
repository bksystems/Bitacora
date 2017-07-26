package com.example.jurizo.bitacora.Core.BitacoraCore.Entity;

/**
 * Created by Carlos Rizo on 25/06/2017.
 */

public class SysDbVersionEntity {

    private int id;
    private String date;
    private int updateOficinas;

    public SysDbVersionEntity(int id, String date, int updateOficinas) {
        this.id = id;
        this.date = date;
        this.updateOficinas = updateOficinas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getupdateOficinas() {
        return updateOficinas;
    }

    public void setupdateOficinas(int updateOficinas) {
        this.updateOficinas = updateOficinas;
    }
}
