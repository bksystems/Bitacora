package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

/**
 * Created by Carlos Rizo on 01/07/2017.
 */

public class ConfigServerConnection {

    private final static String hostname = "http://bitacoralym.esy.es"; //"http://192.168.100.20/BitacoraWS";
    private final static String port = "";
    private final static String pathSyncFiles = "/core/";

    public static String getHostname() {
        return hostname;
    }

    public static String getPort() {
        return port;
    }

    public static String getPathSyncFiles() {
        return pathSyncFiles;
    }
}
