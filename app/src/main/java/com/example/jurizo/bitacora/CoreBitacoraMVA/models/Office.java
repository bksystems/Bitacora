package com.example.jurizo.bitacora.CoreBitacoraMVA.models;

import java.util.Date;

/**
 * Created by jurizo on 26/07/17.
 */

public class Office {
    private int id;
    private int cost_center;
    private int eco_identifier;
    private String direcction;
    private String subdirection;
    private String region;
    private String office;
    private String telephone_company;
    private boolean already_renewed;
    private int segment_logistics;
    private int segment_mobility;
    private int active_employees;
    private int authorized_employees;
    private int devices_assigned;
    private float latitude;
    private float longitude;
    private Date created;
    private Date modified;

    public Office() {
    }

    public Office(int id, int cost_center, int eco_identifier, String direcction, String subdirection, String region, String office, String telephone_company, boolean already_renewed, int segment_logistics, int segment_mobility, int active_employees, int authorized_employees, int devices_assigned, float latitude, float longitude, Date created, Date modified) {
        this.id = id;
        this.cost_center = cost_center;
        this.eco_identifier = eco_identifier;
        this.direcction = direcction;
        this.subdirection = subdirection;
        this.region = region;
        this.office = office;
        this.telephone_company = telephone_company;
        this.already_renewed = already_renewed;
        this.segment_logistics = segment_logistics;
        this.segment_mobility = segment_mobility;
        this.active_employees = active_employees;
        this.authorized_employees = authorized_employees;
        this.devices_assigned = devices_assigned;
        this.latitude = latitude;
        this.longitude = longitude;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost_center() {
        return cost_center;
    }

    public void setCost_center(int cost_center) {
        this.cost_center = cost_center;
    }

    public int getEco_identifier() {
        return eco_identifier;
    }

    public void setEco_identifier(int eco_identifier) {
        this.eco_identifier = eco_identifier;
    }

    public String getDirecction() {
        return direcction;
    }

    public void setDirecction(String direcction) {
        this.direcction = direcction;
    }

    public String getSubdirection() {
        return subdirection;
    }

    public void setSubdirection(String subdirection) {
        this.subdirection = subdirection;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTelephone_company() {
        return telephone_company;
    }

    public void setTelephone_company(String telephone_company) {
        this.telephone_company = telephone_company;
    }

    public boolean isAlready_renewed() {
        return already_renewed;
    }

    public void setAlready_renewed(boolean already_renewed) {
        this.already_renewed = already_renewed;
    }

    public int getSegment_logistics() {
        return segment_logistics;
    }

    public void setSegment_logistics(int segment_logistics) {
        this.segment_logistics = segment_logistics;
    }

    public int getSegment_mobility() {
        return segment_mobility;
    }

    public void setSegment_mobility(int segment_mobility) {
        this.segment_mobility = segment_mobility;
    }

    public int getActive_employees() {
        return active_employees;
    }

    public void setActive_employees(int active_employees) {
        this.active_employees = active_employees;
    }

    public int getAuthorized_employees() {
        return authorized_employees;
    }

    public void setAuthorized_employees(int authorized_employees) {
        this.authorized_employees = authorized_employees;
    }

    public int getDevices_assigned() {
        return devices_assigned;
    }

    public void setDevices_assigned(int devices_assigned) {
        this.devices_assigned = devices_assigned;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
