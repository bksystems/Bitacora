package com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables;

/**
 * Created by Carlos Rizo on 15/07/2017.
 */

public class dbTableAreas {
    public static String TableName = "Areas";

    private static String AreaId = "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static String AreaArea = "area integer ";

    public static String OnCreate = "Create Table " + TableName + "("
            + AreaId
            + AreaArea
            + ");";

    public static String OnDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
