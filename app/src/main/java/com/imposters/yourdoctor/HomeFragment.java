package com.imposters.yourdoctor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imposters.yourdoctor.Assessment;
import com.imposters.yourdoctor.R;

import java.util.Objects;

public class HomeFragment extends Fragment implements View.OnClickListener {

    FirebaseAuth auth;
    private Button t1,t2,t3;
    DatabaseReference db,dbh;
    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tv = root.findViewById(R.id.top);
        auth = FirebaseAuth.getInstance();
        dbh=FirebaseDatabase.getInstance().getReference().child("History").child(auth.getCurrentUser().getUid());

        HorizontalScrollView horizontalScrollView = root.findViewById(R.id.hscr);

        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(linearParams);

        horizontalScrollView.addView(linearLayout);

        LayoutInflater layoutInflater = getLayoutInflater();


        String uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    tv.setText("Welcome, "+dataSnapshot.child("name").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        dbh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                linearLayout.removeAllViews();
                final View s1 = layoutInflater.inflate(R.layout.story,null);
                linearLayout.addView(s1);
                s1.setOnClickListener(v -> startActivity(new Intent(getContext(),History.class)));
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    Story story = dataSnapshot1.getValue(Story.class);
                    final View s = layoutInflater.inflate(R.layout.story,null);
                    TextView tv1=s.findViewById(R.id.history);
                    String x=story.disease;
                    tv1.setText(x.substring(0,x.indexOf('|')));
                    linearLayout.addView(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        t1=root.findViewById(R.id.tab1);
        t1.setOnClickListener(this);
        t2=root.findViewById(R.id.tab2);
        t2.setOnClickListener(this);
        t3=root.findViewById(R.id.tab3);
        t3.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == t1){
            startActivity(new Intent(getContext(), Assessment.class));
        }
        if(v ==t2){
            startActivity(new Intent(getContext(),Consult.class));
        }
        if(v==t3){
            Toast.makeText(getContext(),"Development Under Progress!!",Toast.LENGTH_SHORT).show();
        }
    }
}

