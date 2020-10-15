package com.imposters.yourdoctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference db;
    public EditText n,e,p,pass;
    public Button register;
    public String name;
    private ProgressDialog pd;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Objects.requireNonNull(getSupportActionBar()).hide();
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("Users");
        n = findViewById(R.id.user);
        e = findViewById(R.id.email);
        p = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.reg);
        register.setOnClickListener(this);
        pd = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        if(v==register){
            registerUser();
        }
    }

    private void registerUser() {
        String email=e.getText().toString().trim();
        String password=pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Fields cannot be Blank",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Fields cannot be Blank",Toast.LENGTH_SHORT).show();
            return;
        }
        pd.setMessage("Registering user...");
        pd.show();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, task -> {
                    pd.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(Registration.this,"Registered Successfully.",Toast.LENGTH_SHORT).show();
                        User u=new User(n.getText().toString().trim(),e.getText().toString().trim(),p.getText().toString().trim());
                        db.child(Objects.requireNonNull(auth.getUid())).setValue(u);
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Registration.this,"Could not register. Please try again!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}