package com.example.jurizo.bitacora;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jurizo.bitacora.Controls.Utils;
import com.example.jurizo.bitacora.CoreBitacoraMVA.database.SynchronizationService.LoginSync;

import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private Context context;

    @InjectView(R.id.login_user_name) EditText login_user_name;
    @InjectView(R.id.login_user_password) EditText login_user_password;
    @InjectView(R.id.login_user_signup) Button login_user_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        ButterKnife.inject(this);

        PrepareDataBase();

        login_user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String username = login_user_name.getText().toString();
                    String password = Utils.EncryptPassword(login_user_password.getText().toString());
                    String access_type = "mobile";
                    String access_system = "Android 6.0.1";
                    String ip_address = "pending";
                    String serial_number = getSerialNumber();
                    String imei = getIMEI();
                    String sim_card_number = "pending";

                    LoginSync loginSync = new LoginSync(context);
                    loginSync.execute(username, password, access_type, access_system, ip_address, serial_number, imei, sim_card_number);
                }
            }
        });
    }

    private void PrepareDataBase() {
        try {
            //DBHelper dbHelper = new DBHelper(this);
            //SQLiteDatabase db = dbHelper.getWritableDatabase();
            //dbHelper.onCreate(db);

        }catch (SQLiteException ex){
            Log.i("DB", ex.getMessage());
        }
    }

    private boolean validate() {
        boolean valid = true;
        String user_name = login_user_name.getText().toString();
        String user_password = login_user_password.getText().toString();

        if(user_name.isEmpty()){
            login_user_name.setError("Por favor ingresa tu nómina");
            valid = false;
        }else{
            login_user_name.setError(null);
        }

        if(user_password.isEmpty()){
            login_user_password.setError("Por favor ingresa tu contraseña");
            valid = false;
        }else{
            login_user_password.setError(null);
        }

        return valid;
    }

    public String getSerialNumber(){
        String serialNumber = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNumber = (String) get.invoke(c, "sys.serialnumber", "Error");
            if (serialNumber.equals("Error")) {
                serialNumber = (String) get.invoke(c, "ril.serialnumber", "Error");
            }
        }catch (Exception ex){

        }
        return serialNumber;

    }

    public String getIMEI(){
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return imei;
    }

}
