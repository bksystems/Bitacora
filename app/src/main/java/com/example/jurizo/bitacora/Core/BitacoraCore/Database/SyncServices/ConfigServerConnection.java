package com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices;

/**
 * Created by Carlos Rizo on 01/07/2017.
 */

public class ConfigServerConnection {

    private final static String hostname = "http://cblymbitacora.info"; //"http://bitacoralym.esy.es"; //
    private final static String port = "";
    private final static String pathServices = "/ws/mobile/";

    //Rutas para Users
    private final static String URLLogin = hostname + port + pathServices +  "users/login.ini.php";
    private final static String URLGetDepartments = hostname + port + pathServices + "departments/get_departments.php";



    private final static String getURLOficinas = hostname + port + pathServices + "getOficinas.php";
    private final static String getURLPuestos = hostname + port + pathServices + "getPuestos.php";
    private final static String getURLAreas = hostname + port + pathServices + "getAreas.php";
    private final static String getURLVisitas = hostname + port + pathServices + "getVisitas.php";
    private final static String URLOffices = hostname + port + pathServices + "getVisitas.php";

    public static String getHostname() {
        return hostname;
    }

    public static String getPort() {
        return port;
    }

    public static String getPathSyncFiles() {
        return pathServices;
    }

    public static String getURLOficinas() {
        return getURLOficinas;
    }

    public static String getURLPuestos() {
        return getURLPuestos;
    }

    public static String getURLAreas() {
        return getURLAreas;
    }

    public static String getURLVisitas() {
        return getURLVisitas;
    }

    public static String getURLOffices() {
        return URLOffices;
    }

    //Propiedades para el servicio de Goddady
    public static String URL_LoginValidate() {
        return URLLogin;
    }

    public static String getURLDepartmanets() {
        return URLGetDepartments;
    }
}
