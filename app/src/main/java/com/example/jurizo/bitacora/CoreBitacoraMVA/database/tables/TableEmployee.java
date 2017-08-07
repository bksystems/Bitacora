package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by jurizo on 27/07/17.
 */

public class TableEmployee {

    public static String TableName = "employees";

    private static String Employee_id = "id integer primary key autoincrement, ";
    private static String Employee_roster = "roster integer not null, ";
    private static String Employee_first_lastname = "first_lastname text not null, ";
    private static String Employee_second_lastname = "second_lastname text null, ";
    private static String Employee_names = "names text not null, ";
    private static String Employee_email = "email text not null, ";
    private static String Employee_department_id = "department_id integer not null, ";
    private static String Employee_position_employee_id = "position_employee_id integer null, ";
    private static String Employee_employee_id = "employee_id integer null, ";
    private static String Employee_created = "created text null, ";
    private static String Employee_modified = "modified text null";

    public static String onCreate = "Create Table " + TableName + " ("
            + Employee_id
            + Employee_roster
            + Employee_first_lastname
            + Employee_second_lastname
            + Employee_names
            + Employee_email
            + Employee_department_id
            + Employee_position_employee_id
            + Employee_employee_id
            + Employee_created
            + Employee_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
