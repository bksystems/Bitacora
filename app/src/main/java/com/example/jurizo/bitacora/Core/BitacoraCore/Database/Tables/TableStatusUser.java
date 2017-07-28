package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class TableStatusUser {
    public static String TableName = "status_users";

    private static String StatusUser_id = "id integer primary key autoincrement, ";
    private static String StatusUser_status = "segment text not null, ";
    private static String StatusUser_description = "description text not null, ";
    private static String StatusUser_created = "created text null, ";
    private static String StatusUser_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + StatusUser_id
            + StatusUser_status
            + StatusUser_description
            + StatusUser_created
            + StatusUser_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
