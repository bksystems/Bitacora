package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class TableRol {
    public static String TableName = "rols";

    private static String Rol_id = "id integer primary key autoincrement, ";
    private static String Rol_rol = "rol text not null, ";
    private static String Rol_description = "description text not null, ";
    private static String Rol_created = "created text null, ";
    private static String Rol_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + Rol_id
            + Rol_rol
            + Rol_description
            + Rol_created
            + Rol_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
