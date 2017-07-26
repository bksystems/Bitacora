package com.example.jurizo.bitacora.Core.BitacoraCore.Database.Tables;

/**
 * Created by jurizo on 29/06/17.
 */

public class dbTableUsers {
    public static String TableName = "Users";

    private static String UserId = "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static String UserNomina = "nomina integer, ";
    private static String UserApellidoPaterno = "apellido_paterno text not null, ";
    private static String UserApellidoMaterno= "apellido_materno text not null, ";
    private static String UserNombres = "nombres text not null, ";
    private static String UserEmail = "email text not null, ";
    private static String UserPassword = "password text not null, ";
    private static String UserStatus = "id_status integer null, ";
    private static String UserIdJefe = "id_jefe integer, ";
    private static String UserIdPuesto = "id_puesto integer null, ";
    private static String UserIdArea = "id_area integer null, ";
    private static String UserToken = "token text not null, ";
    private static String UserTokenFinish = "tokenFinish text not null ";

    public static String OnCreate = "Create Table " + TableName + "("
            + UserId
            + UserNomina
            + UserApellidoPaterno
            + UserApellidoMaterno
            + UserNombres
            + UserEmail
            + UserPassword
            + UserStatus
            + UserIdJefe
            + UserIdPuesto
            + UserIdArea
            + UserToken
            + UserTokenFinish
            + ");";

    public static String OnDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
