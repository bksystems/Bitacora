package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by jurizo on 26/07/17.
 */

public class TableLogs {

    public static String TableName = "Logs";

    private static String Logs_id = "id integer primary key autoincrement, ";
    private static String Logs_tag = "tag text null, ";
    private static String Logs_class = "class text null, ";
    private static String Logs_error = "error text null, ";
    private static String Logs_datetime = "datetime text null, ";
    private static String Logs_isLoad = "isLoad integer null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + Logs_id
            + Logs_tag
            + Logs_class
            + Logs_error
            + Logs_datetime
            + Logs_isLoad + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
