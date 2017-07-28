package com.example.jurizo.bitacora.Core.BitacoraCore.Database.SyncServices;

/**
 * Created by Carlos Rizo on 01/07/2017.
 */

public class ConfigServerConnection {

    private final static String hostname = "http://192.168.100.20/Bitacora"; //"http://bitacoralym.esy.es"; //
    private final static String port = "";
    private final static String pathServices = "/ws/mobile/";

    //Rutas para Users
    private final static String URLLogin = hostname + port + pathServices +  "users/login.ini.php";

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

    public static String URL_LoginValidate() {
        return URLLogin;
    }
}
