package com.example.jurizo.bitacora.CoreBitacoraMVA.controllers;

import android.content.Context;
import android.util.Log;

import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.EmployeeDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.dataAccessObject.LogsDAO;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.ConfigServerConnection;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.HttpHandler;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Department;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Employee;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos_Rizo on 07/08/17.
 */

public class EmployeeController {
    private final String TAG = "C_Employee";
    private final String TAGClass = getClass().getName();
    private final Context context;

    public EmployeeController(Context context) {
        this.context = context;
    }

    public Employee getEmployeeLoginByServer(int id_employee){
        Employee emp = null;
        try {
            URL url = new URL(ConfigServerConnection.getURLEmployeeById());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("employee_id", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(id_employee), "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();

            List<Employee> employees = JSON_Parse_Employees(result);
            if(employees != null && employees.size() > 0){
                emp = employees.get(0);
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            emp = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        } catch (IOException ex) {
            ex.printStackTrace();
            emp = null;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return emp;
    }

    public boolean Download_Update_Employee(String tocken, Employee emp) {
        boolean result = false;
        try {
            HttpHandler httpHandler = new HttpHandler();
            String jsonResponse = httpHandler.makeServicesCall(ConfigServerConnection.getURLEmployees());
            List<Employee> employees = JSON_Parse_Employees(jsonResponse);
            if(employees != null && employees.size() > 0){
                if(updateAllEmployees(filteredOnlyAsigned(employees, emp))){
                    result = true;
                }
            }
        }catch (Exception ex){
            result = false;
            Log.d(TAG, ex.getMessage());
            LogsDAO logs = new LogsDAO(TAG, TAGClass, ex.getMessage(), context);
        }
        return result;
    }

    private List<Employee> JSON_Parse_Employees(String jsonResponse) {
        List<Employee> employees = null;
        try {
            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray jsonUEmployeeData = jsonObj.getJSONArray("data");
            if (jsonUEmployeeData.length() > 0) {
                for (int i = 0; i < jsonUEmployeeData.length(); i++) {
                    JSONObject itemObj = jsonUEmployeeData.getJSONObject(i);
                    boolean result = itemObj.getBoolean("success");
                    if(result) {
                        employees = new ArrayList<>();
                        JSONArray jsonUEmployeeEmployee = itemObj.getJSONArray("employees");
                        for(int j = 0; j < jsonUEmployeeEmployee.length(); j++){
                            JSONObject itemObjEmployee = jsonUEmployeeEmployee.getJSONObject(j);
                            int id = itemObjEmployee.getInt("id");
                            int roster = itemObjEmployee.getInt("roster");
                            String first_lastname = itemObjEmployee.getString("first_lastname");
                            String second_lastname = itemObjEmployee.getString("second_lastname");
                            String names = itemObjEmployee.getString("names");
                            String email= itemObjEmployee.getString("email");
                            int department_id = itemObjEmployee.getInt("department_id");
                            int position_employee_id = itemObjEmployee.getInt("position_employee_id");
                            int status_employee_id = itemObjEmployee.getInt("status_employee_id");
                            int employee_id = itemObjEmployee.getInt("employee_id");
                            String created = itemObjEmployee.getString("created");
                            String modified = itemObjEmployee.getString("modified");
                            Employee objEmployee = new Employee(id, roster, first_lastname, second_lastname, names, email, department_id, position_employee_id, status_employee_id, employee_id, created, modified);
                            employees.add(objEmployee);
                        }

                    }else{
                        employees = null;
                    }
                }
            } else {
                employees = null;
            }
        } catch (final JSONException e) {
            Log.e("ParseDepartment", "Json parsing error: " + e.getMessage());
        }
        return employees;
    }

    private boolean updateAllEmployees(List<Employee> employees) {
        EmployeeDAO employeeDAO = new EmployeeDAO(context);
        return employeeDAO.updateAll(employees);
    }

    private List<Employee> filteredOnlyAsigned(List<Employee> employees, Employee emp) {
        List<Employee> asignated = null;
        if(employees != null && employees.size()>0 && emp != null){
            asignated = new ArrayList<>();
            asignated.add(emp);
            for(int i = 0; i < asignated.size(); i++){
                int idsearch = asignated.get(i).getId();
                for(Employee item : employees){
                    if(item.getEmployee_id() == idsearch){
                        asignated.add(item);
                    }
                }
            }
        }
        return asignated;
    }
}
