package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class TablePositionsEmployee {

    public static String TableName = "position_employees";

    private static String PositionsEmployee_id = "id integer primary key autoincrement, ";
    private static String PositionsEmployee_position = "position text not null, ";
    private static String PositionsEmployee_description = "description text not null, ";
    private static String PositionsEmployee_created = "created text null, ";
    private static String PositionsEmployee_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + PositionsEmployee_id
            + PositionsEmployee_position
            + PositionsEmployee_description
            + PositionsEmployee_created
            + PositionsEmployee_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
