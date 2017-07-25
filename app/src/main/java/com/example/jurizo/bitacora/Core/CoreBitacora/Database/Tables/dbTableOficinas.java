package com.example.jurizo.bitacora.Core.CoreBitacora.Database.Tables;

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
    private static String OficinaSegmento = "segmento text not null, ";
    private static String OficinaRenovada = "renovada integer null, ";
    private static String OficinaCuentaCI = "cuenta_ci integer null, ";
    private static String OficinaCarrier = "carrier text null, ";
    private static String OficinaPlantillaAutorizada = "plantilla_autorizada integer null, ";
    private static String OficinaPlantillaVentas = "plantilla_ventas integer null, ";
    private static String OficinaInventario_dm = "inventario_dm int null, ";
    private static String OficinaLatitud = "latitud float null, ";
    private static String OficinaLongitud = "longitud float null ";

    public static String OnCreate = "Create Table " + TableName + "("
            + OficinaId
            + OficinaCC
            + OficinaDireccion
            + OficinaSubdireccion
            + OficinaRegion
            + OficinaOficina
            + OficinaSegmento
            + OficinaRenovada
            + OficinaCuentaCI
            + OficinaCarrier
            + OficinaPlantillaAutorizada
            + OficinaPlantillaVentas
            + OficinaInventario_dm
            + OficinaLatitud
            + OficinaLongitud
            + ");";

    public static String OnDelete = "DROP TABLE IF EXISTS " + TableName + ";";

}
