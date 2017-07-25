package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

/**
 * Created by Carlos Rizo on 01/07/2017.
 */

public class ConfigServerConnection {

    private final static String hostname = "http://bitacoralym.esy.es"; //"http://192.168.100.20/BitacoraWS";
    private final static String port = "";
    private final static String pathSyncFiles = "/core/wservices_mobile/";

    private final static String getURLOficinas = hostname + port + pathSyncFiles + "getOficinas.php";
    private final static String getURLPuestos = hostname + port + pathSyncFiles + "getPuestos.php";
    private final static String getURLAreas = hostname + port + pathSyncFiles + "getAreas.php";
    private final static String getURLVisitas = hostname + port + pathSyncFiles + "getVisitas.php";

    public static String getHostname() {
        return hostname;
    }

    public static String getPort() {
        return port;
    }

    public static String getPathSyncFiles() {
        return pathSyncFiles;
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
}
