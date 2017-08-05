package com.example.jurizo.bitacora.CoreBitacoraMVA.database.tables;

/**
 * Created by Carlos Rizo on 27/07/2017.
 */

public class TableQuestionSegment {

    public static String TableName = "question_segments";

    private static String QuestionSegment_id = "id integer primary key autoincrement, ";
    private static String QuestionSegment_segment_id = "segment_id integer not null, ";
    private static String QuestionSegment_question = "question text not null, ";
    private static String QuestionSegment_active = "active integer not null, ";
    private static String QuestionSegment_created = "created text null, ";
    private static String QuestionSegment_modified = "modified text null ";

    public static String onCreate = "Create Table " + TableName + " ("
            + QuestionSegment_id
            + QuestionSegment_segment_id
            + QuestionSegment_question
            + QuestionSegment_active
            + QuestionSegment_created
            + QuestionSegment_modified + ")";

    public static String onDelete = "DROP TABLE IF EXISTS " + TableName + ";";
}
