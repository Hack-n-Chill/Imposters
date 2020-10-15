package com.imposters.yourdoctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class History extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    List<String> symptom = new ArrayList<>();
    List<String> disease = new ArrayList<>();
    List<String> medication = new ArrayList<>();
    DatabaseReference db;
    FirebaseAuth auth;
    LayoutInflater layoutInflater;
    AutoCompleteTextView atv1,atv2,atv3;
    String[] diseases = new String[]{"Drug Reaction","Malaria","Allergy","Hypothyroidism","Psoriasis","GERD","Chronic cholestasis","hepatitis A","Osteoarthristis"," (vertigo) Paroymsal  Positional Vertigo","Hypoglycemia"," Acne"," Diabetes"," Impetigo"," Hypertension"," Peptic ulcer diseae"," Dimorphic hemorrhoids(piles)"," Common Cold"," Chicken pox","Cervical spondylosis","Hyperthyroidism","Urinary tract infection","Varicose veins","AIDS"," Paralysis (brain hemorrhage)","Typhoid","Hepatitis B","Fungal infection","Hepatitis C","Migraine","Bronchial Asthma"," Alcoholic hepatitis","Jaundice"," Hepatitis E","Dengue","Hepatitis D","Heart attack","Pneumonia"," Arthritis"," Gastroenteritis","Tuberculosis"};
    String[] symptoms=new String[]{"Fungal infection", "itching", "skin rash", "nodal skin eruptions", "dischromic patches", "Allergy", "continuous sneezing", "shivering", "chills", "watering from eyes", "GERD", "stomach pain", "acidity", "ulcers on tongue", "vomiting", "cough", "chest pain", "Chronic cholestasis", "yellowish skin", "nausea", "loss of appetite", "abdominal pain", "yellowing of eyes", "Drug Reaction", "burning micturition", "spotting  urination", "Peptic ulcer diseae", "passage of gases", "internal itching", "indigestion", "AIDS", "muscle wasting", "patches in throat", "high fever", "extra marital contacts", "Diabetes ", "fatigue", "weight loss", "restlessness", "lethargy", "irregular sugar level", "blurred and distorted vision", "obesity", "excessive hunger", "increased appetite", "polyuria", "Gastroenteritis", "sunken eyes", "dehydration", "diarrhoea", "Bronchial Asthma", "breathlessness", "family history", "mucoid sputum", "Hypertension ", "headache", "dizziness", "loss of balance", "lack of concentration", "Migraine", "stiff neck", "depression", "irritability", "visual disturbances", "Cervical spondylosis", "back pain", "weakness in limbs", "neck pain", "Paralysis (brain hemorrhage)", "weakness of one body side", "altered sensorium", "Jaundice", "dark urine", "Malaria", "sweating", "muscle pain", "Chicken pox", "mild fever", "swelled lymph nodes", "malaise", "red spots over body", "Dengue", "joint pain", "pain behind the eyes", "Typhoid", "constipation", "toxic look (typhos)", "belly pain", "hepatitis A", "Hepatitis B", "yellow urine", "receiving blood transfusion", "receiving unsterile injections", "Hepatitis C", "Hepatitis D", "Hepatitis E", "coma", "stomach bleeding", "acute liver failure", "Alcoholic hepatitis", "swelling of stomach", "distention of abdomen", "history of alcohol consumption", "fluid overload", "Tuberculosis", "phlegm", "blood in sputum", "Common Cold", "throat irritation", "redness of eyes", "sinus pressure", "runny nose", "congestion", "loss of smell", "Pneumonia", "fast heart rate", "rusty sputum", "Dimorphic hemmorhoids(piles)", "pain during bowel movements", "pain in anal region", "bloody stool", "irritation in anus", "Heart attack", "Varicose veins", "cramps", "bruising", "swollen legs", "swollen blood vessels", "prominent veins on calf", "Hypothyroidism", "weight gain", "cold hands and feets", "mood swings", "puffy face and eyes", "enlarged thyroid", "brittle nails", "swollen extremeties", "abnormal menstruation", "Hyperthyroidism", "muscle weakness", "Hypoglycemia", "anxiety", "slurred speech", "palpitations", "drying and tingling lips", "Osteoarthristis", "knee pain", "hip joint pain", "swelling joints", "painful walking", "Arthritis", "movement stiffness", "(vertigo) Paroymsal  Positional Vertigo", "spinning movements", "unsteadiness", "Acne", "pus filled pimples", "blackheads", "scurring", "Urinary tract infection", "bladder discomfort", "foul smell of urine", "continuous feel of urine", "Psoriasis", "skin peeling", "silver like dusting", "small dents in nails", "inflammatory nails", "Impetigo", "blister", "red sore around nose", "yellow crust ooze"};
    String[] medications;
    HorizontalScrollView hscr1,hscr2,hscr3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    Button s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance().getReference().child("History").child(auth.getCurrentUser().getUid());

        s=findViewById(R.id.save);
        s.setOnClickListener(this);

        layoutInflater = getLayoutInflater();
        hscr1=findViewById(R.id.hsym);
        hscr2 = findViewById(R.id.hdis);
        hscr3 = findViewById(R.id.hmed);

        atv1=findViewById(R.id.sym);
        atv2=findViewById(R.id.dis);
        atv3=findViewById(R.id.med);

        atv1.setAdapter(new ArrayAdapter<>(History.this, android.R.layout.simple_list_item_1, symptoms));
        atv1.setThreshold(0);
        atv1.setOnItemClickListener(this);

        atv2.setAdapter(new ArrayAdapter<>(History.this, android.R.layout.simple_list_item_1, diseases));
        atv2.setThreshold(0);
        atv2.setOnItemClickListener(this);

        linearLayout1 = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setLayoutParams(linearParams);
        hscr1.addView(linearLayout1);

        linearLayout2 = new LinearLayout(getApplicationContext());
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout2.setLayoutParams(linearParams);
        hscr2.addView(linearLayout2);

        linearLayout3 = new LinearLayout(getApplicationContext());
        linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout3.setLayoutParams(linearParams);
        hscr3.addView(linearLayout3);



    }

    @Override
    public void onClick(View v) {
        if(v==s){
            String a1="",a2="",a3="";
            for(String s: symptom){
                a1=a1+s+"|";
            }
            for(String s: disease){
                a2=a2+s+"|";
            }
            for(String s: medication){
                a3=a3+s+"|";
            }
            Story story=new Story(a1,a2,a3,"1");
            db.push().setValue(story);
            Toast.makeText(this, "History Noted.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(atv1.hasFocus()){
            String item = parent.getItemAtPosition(position).toString();
            atv1.setText("");
            final View v1=layoutInflater.inflate(R.layout.tab,null);
            TextView pref = v1.findViewById(R.id.text);
            pref.setText(item);
            linearLayout1.addView(v1);
            symptom.add(item);
            v1.setOnClickListener(v10 ->{
                symptom.remove(item);
                v1.setVisibility(View.GONE);
            });
        }
        if(atv2.hasFocus()){
            String item = parent.getItemAtPosition(position).toString();
            atv2.setText("");
            final View v1=layoutInflater.inflate(R.layout.tab,null);
            TextView pref = v1.findViewById(R.id.text);
            pref.setText(item);
            linearLayout2.addView(v1);
            disease.add(item);
            v1.setOnClickListener(v10 -> {
                disease.remove(item);
                v1.setVisibility(View.GONE);
            });
        }
    }
}