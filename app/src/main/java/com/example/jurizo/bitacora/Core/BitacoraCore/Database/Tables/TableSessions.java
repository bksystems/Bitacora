package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by Carlos_Rizo on 04/08/17.
 */

public class TableSessions {

    public static String TableName = "sessions";

    private static String Session_id = "id integer primary key autoincrement, ";
    private static String Session_user_id = "user_id integer not null, ";
    private static String Session_tocken = "tocken text not null, ";
    private static String Session_finish_tocken = "finish_tocken text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + Session_id
            + Session_user_id
            + Session_tocken
            + Session_finish_tocken + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
