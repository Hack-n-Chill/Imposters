package com.imposters.yourdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    EditText usn,pwd;
    Button log;
    TextView reg;
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            finish();
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        progressBar=new ProgressDialog(this);
        usn=findViewById(R.id.user);
        pwd=findViewById(R.id.pass);
        log=findViewById(R.id.log);
        reg=findViewById(R.id.tvreg);
        log.setOnClickListener(this);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==log){
            Login();
        }
        if(v==reg){
            startActivity(new Intent(getApplicationContext(),Registration.class));
        }
    }

    private void Login()
    {
        String email=usn.getText().toString().trim();
        String password=pwd.getText().toString().trim();
        if(email.equals(""))
        {
            Toast.makeText(Login.this,"Enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals(""))
        {
            Toast.makeText(Login.this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setMessage("Logging in...");
        progressBar.show();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(Login.this, "Login Unsuccessful. Please Check email and password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Login.this,MainActivity.class));
    }
}
