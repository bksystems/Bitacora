package com.example.jurizo.bitacora.Core.CoreBitacora.Entity;

/**
 * Created by jurizo on 22/06/17.
 */

public class EntityOficina {

    private int id;
    private int cc;
    private String direccion;
    private String subdireccion;
    private String region;
    private String oficina;
    private String segmento;
    private int renovada;
    private int cuenta_ci;
    private String carrier;

    public EntityOficina(int id, int cc, String direccion, String subdireccion, String region, String oficina, String segmento, int renovada, int cuenta_ci, String carrier) {
        this.id = id;
        this.cc = cc;
        this.direccion = direccion;
        this.subdireccion = subdireccion;
        this.region = region;
        this.oficina = oficina;
        this.segmento = segmento;
        this.renovada = renovada;
        this.cuenta_ci = cuenta_ci;
        this.carrier = carrier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSubdireccion() {
        return subdireccion;
    }

    public void setSubdireccion(String subdireccion) {
        this.subdireccion = subdireccion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public int getRenovada() {
        return renovada;
    }

    public void setRenovada(int renovada) {
        this.renovada = renovada;
    }

    public int getCuenta_ci() {
        return cuenta_ci;
    }

    public void setCuenta_ci(int cuenta_ci) {
        this.cuenta_ci = cuenta_ci;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
