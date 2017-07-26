package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by jurizo on 29/06/17.
 */

public class dbTableVisits {
    public static String TableName = "Visitas";

    private static String VisitId = "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static String VisitFecha = "fecha text not null, ";
    private static String VisitUserId = "user_id integer not null, ";
    private static String VisitOficinaId = "oficina_id integer not null, ";
    private static String VisitIsUpdate = "Isupdate integer not null, ";
    private static String VisitStatus = "status integer not null ";


    public static String OnCreate = "Create Table " + TableName + "("
            + VisitId
            + VisitFecha
            + VisitUserId
            + VisitOficinaId
            + VisitIsUpdate
            + VisitStatus
            + ");";

    public static String OnDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
