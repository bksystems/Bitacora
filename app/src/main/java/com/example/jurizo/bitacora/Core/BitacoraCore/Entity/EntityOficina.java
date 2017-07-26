package com.example.jurizo.bitacora.Core.BitacoraCore.Entity;

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
    private int plantilla_autorizada;
    private int plantilla_ventas;
    private int inventario_dm;
    private float latitud;
    private float longitud;

    public EntityOficina(int id, int cc, String direccion, String subdireccion,
                         String region, String oficina, String segmento, int renovada, int cuenta_ci,
                         String carrier, int plantilla_autorizada, int plantilla_ventas, int inventario_dm, float latitud, float longitud) {
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
        this.plantilla_autorizada = plantilla_autorizada;
        this.plantilla_ventas = plantilla_ventas;
        this.inventario_dm = inventario_dm;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public int getPlantilla_autorizada() {
        return plantilla_autorizada;
    }

    public void setPlantilla_autorizada(int plantilla_autorizada) {
        this.plantilla_autorizada = plantilla_autorizada;
    }

    public int getPlantilla_ventas() {
        return plantilla_ventas;
    }

    public void setPlantilla_ventas(int plantilla_ventas) {
        this.plantilla_ventas = plantilla_ventas;
    }

    public int getInventario_dm() {
        return inventario_dm;
    }

    public void setInventario_dm(int inventario_dm) {
        this.inventario_dm = inventario_dm;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
