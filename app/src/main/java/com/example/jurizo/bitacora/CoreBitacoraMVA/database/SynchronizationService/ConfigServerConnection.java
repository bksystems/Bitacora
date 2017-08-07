package com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService;

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
    private final static String URLGetSegments = hostname + port + pathServices + "segments/get_segments.php";
    private final static String URLGetQuestionSegments = hostname + port + pathServices + "question_segments/get_questions_segments.php";
    private final static String URLGetAnswareSegments = hostname + port + pathServices + "answers_segments/get_answers_segments.php";
    private final static String URLGetOffices= hostname + port + pathServices + "offices/get_offices.php";
    private final static String URLGetEmployees= hostname + port + pathServices + "employees/get_employees_app.php";
    private final static String URLGetEmployeeById= hostname + port + pathServices + "employees/get_employees_by_id.php";

    public static String getHostname() {
        return hostname;
    }

    public static String getPort() {
        return port;
    }

    public static String getPathSyncFiles() {
        return pathServices;
    }

    //Propiedades para el servicio de Goddady
    public static String URL_LoginValidate() {
        return URLLogin;
    }

    public static String getURLDepartmanets() {
        return URLGetDepartments;
    }

    public static String getURLOffices() {
        return URLGetOffices;
    }

    public static String getURLSegments() {
        return URLGetSegments;
    }

    public static String getURLQuestionSegments(){return URLGetQuestionSegments; }

    public static String getURLAnswareSegments(){return URLGetAnswareSegments; }

    public static String getURLEmployees() {
        return URLGetEmployees;
    }

    public static String getURLEmployeeById() {
        return URLGetEmployeeById;
    }
}
