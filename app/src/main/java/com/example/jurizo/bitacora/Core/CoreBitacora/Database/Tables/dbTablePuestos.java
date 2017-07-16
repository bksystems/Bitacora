package com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables;

/**
 * Created by Carlos Rizo on 15/07/2017.
 */

public class dbTablePuestos {
    public static String TableName = "Puestos";

    private static String PuestoId = "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static String PuestoPuesto = "puesto integer ";

    public static String OnCreate = "Create Table " + TableName + "("
            + PuestoId
            + PuestoPuesto
            + ");";

    public static String OnDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
