package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by jurizo on 27/07/17.
 */

public class TableDepartment {
    public static String TableName = "departments";

    private static String Department_id = "id integer primary key autoincrement, ";
    private static String Department_department = "department text not null, ";
    private static String Department_description = "description text not null, ";
    private static String Department_created = "created text null, ";
    private static String Department_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + Department_id
            + Department_department
            + Department_description
            + Department_created
            + Department_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
