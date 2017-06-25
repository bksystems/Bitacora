package com.example.jurizo.bitacora.Core.CoreBitacora.Database;

/**
 * Created by jurizo on 22/06/17.
 */

public class dbTableOficinas {

    public static String TableName = "Oficinas";
    private static String OficinaId = "id INTEGER PRIMARY KEY AUTOINCREMENT, ";
    private static String OficinaCC = "cc integer, ";
    private static String OficinaDireccion = "direccion text not null, ";
    private static String OficinaSubdireccion = "subdireccion text not null, ";
    private static String OficinaRegion = "region text not null, ";
    private static String OficinaOficina = "oficina text not null, ";
    private static String OficinaSegmento = "segmento integer not null ";

    public static String OnCreate = "Create Table " + TableName + "("
            + OficinaId
            + OficinaCC
            + OficinaDireccion
            + OficinaSubdireccion
            + OficinaRegion
            + OficinaOficina
            + OficinaSegmento
            + ");";

    public static String OnDelete = "Drop Table " + TableName + ";";

}
