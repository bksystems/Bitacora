package com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables;

/**
 * Created by jurizo on 29/06/17.
 */

public class dbTableUsers {
    public static String TableName = "Users";

    private static String UserId = "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static String UserNomina = "nomina integer, ";
    private static String UserApellidoPaterno = "apellido_paterno text not null, ";
    private static String UserApellidoMaterno= "apellido_materno text not null, ";
    private static String UserNombtres = "nombres text not null, ";
    private static String UserEmail = "email text not null, ";
    private static String UserPassword = "password text not null, ";
    private static String UserStatus = "status integer null, ";
    private static String UserIdJefe = "id_jefe integer, ";
    private static String UserToken = "token text not null, ";
    private static String UserTokenFinish = "tokeFinish text not null ";

    public static String OnCreate = "Create Table " + TableName + "("
            + UserId
            + UserNomina
            + UserApellidoPaterno
            + UserApellidoMaterno
            + UserNombtres
            + UserEmail
            + UserPassword
            + UserStatus
            + UserIdJefe
            + UserToken
            + UserTokenFinish
            + ");";

    public static String OnDelete = "Drop Table " + TableName + ";";
}
