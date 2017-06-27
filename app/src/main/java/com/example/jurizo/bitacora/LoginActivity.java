package com.example.jurizo.bitacora;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.login_user_name) EditText login_user_name;
    @InjectView(R.id.login_user_password) EditText login_user_password;
    @InjectView(R.id.login_user_signup) Button login_user_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ButterKnife.inject(this);
        
        login_user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        Log.d(TAG, "Siggup");
        if(!validate()){
            onSignUpFailed();
            return;
        }

        login_user_signup.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Validando usuario");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onSignUpSuccess();
                        progressDialog.dismiss();
                    }
                }, 4000);

    }

    private void onSignUpSuccess() {
        login_user_password.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }


    private void onSignUpFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        login_user_password.setEnabled(true);
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
}
