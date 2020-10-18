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

public class Health extends AppCompatActivity {

    DatabaseReference db;
    TextView tv;
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Your Doctor");
        db= FirebaseDatabase.getInstance().getReference().child("Concerns");
        tv = findViewById(R.id.tv);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                s="";
                for(DataSnapshot ds1: snapshot.getChildren()){
                    Concerns d=ds1.getValue(Concerns.class);
                    s=s+d.title+"\n\n"+"Precautions: "+d.precautions+"\n\n"+"Symptoms: "+d.symptoms+"\n\n"+"Cure: "+d.cure+"\n\n\n";
                }
                tv.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}