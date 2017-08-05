package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class TableStatusEmployee {
    public static String TableName = "status_employees";

    private static String StatusEmployee_id = "id integer primary key autoincrement, ";
    private static String StatusEmployee_status = "segment text not null, ";
    private static String StatusEmployee_description = "description text not null, ";
    private static String StatusEmployee_created = "created text null, ";
    private static String StatusEmployee_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + StatusEmployee_id
            + StatusEmployee_status
            + StatusEmployee_description
            + StatusEmployee_created
            + StatusEmployee_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
