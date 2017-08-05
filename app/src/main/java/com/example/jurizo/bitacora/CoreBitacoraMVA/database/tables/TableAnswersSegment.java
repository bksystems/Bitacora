package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by jurizo on 27/07/17.
 */

public class TableAnswersSegment {

    public static String TableName = "answers_segments";

    private static String AnswersSegment_id = "id integer primary key autoincrement, ";
    private static String AnswersSegment_questions_segment_id = "questions_segment_id integer not null, ";
    private static String AnswersSegment_answare = "answare text not null, ";
    private static String AnswersSegment_active = "active integer not null, ";
    private static String AnswersSegment_created = "created text null, ";
    private static String AnswersSegment_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + AnswersSegment_id
            + AnswersSegment_questions_segment_id
            + AnswersSegment_answare
            + AnswersSegment_active
            + AnswersSegment_created
            + AnswersSegment_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";

}
