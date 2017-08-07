package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by jurizo on 27/07/17.
 */

public class TableUser {

    public static String TableName = "users";

    private static String Users_id = "id integer primary key autoincrement, ";
    private static String Users_employee_id = "employee_id integer not null, ";
    private static String Users_username = "username integer not null, ";
    private static String Users_password = "password text not null, ";
    private static String Users_status_user_id = "status_user_id integer not null, ";
    private static String Users_rol_id = "rol_id integer not null, ";
    private static String Users_created = "created text not null, ";
    private static String Users_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + Users_id
            + Users_employee_id
            + Users_username
            + Users_password
            + Users_status_user_id
            + Users_rol_id
            + Users_created
            + Users_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";

}
