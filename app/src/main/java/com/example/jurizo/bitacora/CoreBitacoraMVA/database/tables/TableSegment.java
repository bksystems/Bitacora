package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class TableSegment {
    public static String TableName = "segments";

    private static String Segment_id = "id integer primary key autoincrement, ";
    private static String Segment_department = "department text not null, ";
    private static String Segment_segment = "segment text not null, ";
    private static String Segment_description = "description text not null, ";
    private static String Segment_created = "created text null, ";
    private static String Segment_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + Segment_id
            + Segment_department
            + Segment_segment
            + Segment_description
            + Segment_created
            + Segment_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
