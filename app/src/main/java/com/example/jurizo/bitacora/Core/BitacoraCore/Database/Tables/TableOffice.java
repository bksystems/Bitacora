package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by jurizo on 26/07/17.
 */

public class TableOffice {

    public static String TableName = "Offices";

    private static String Office_id = "id integer primary key autoincrement, ";
    private static String Office_cost_center = "cost_center integer not null, ";
    private static String Office_eco_identifier = "eco_identifier integer, ";
    private static String Office_direcction = "direcction text not null, ";
    private static String Office_subdirection = "subdirection text not null, ";
    private static String Office_region = "region text not null, ";
    private static String Office_office = "office text not null, ";
    private static String Office_telephone_company = "telephone_company text null, ";
    private static String Office_already_renewed = "already_renewed integer null, ";
    private static String Office_segment_logistics = "segment_logistics integer null, ";
    private static String Office_segment_mobility = "segment_mobility integer null, ";
    private static String Office_active_employees = "active_employees integer null, ";
    private static String Office_authorized_employees = "authorized_employees integer null, ";
    private static String Office_devices_assigned = "devices_assigned integer null, ";
    private static String Office_latitude = "latitude float, ";
    private static String Office_longitude = "longitude float null, ";
    private static String Office_created = "created text null, ";
    private static String Office_modified = "modified text null";

    public static String onCreate = "Create Table " + TableName + " ("
            + Office_id
            + Office_cost_center
            + Office_eco_identifier
            + Office_direcction
            + Office_subdirection
            + Office_region
            + Office_office
            + Office_telephone_company
            + Office_already_renewed
            + Office_segment_logistics
            + Office_segment_mobility
            + Office_active_employees
            + Office_authorized_employees
            + Office_devices_assigned
            + Office_latitude
            + Office_longitude
            + Office_created
            + Office_modified + ")";

    public static String OnDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
