package com.imposters.yourdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Consult extends AppCompatActivity {

    DatabaseReference db;
    TextView tv;
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Your Doctor");
        db= FirebaseDatabase.getInstance().getReference().child("Doctors");
        tv = findViewById(R.id.tv);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                s="";
                for(DataSnapshot ds1: snapshot.getChildren()){
                    doctor d=ds1.getValue(doctor.class);
                    s=s+d.name+"\n"+d.specialization+"\n"+"Ph.: "+d.contact+"\n\n";
                }
                tv.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}