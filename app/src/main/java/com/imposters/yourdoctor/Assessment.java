package com.imposters.yourdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assessment extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    AutoCompleteTextView atv;
    TextView pref,status;
    LayoutInflater layoutInflater;
    TableLayout tableLayout;
    ScrollView scrollView;
    String[] d=new String[]{"Fungal infection", "itching", "skin rash", "nodal skin eruptions", "dischromic patches", "Allergy", "continuous sneezing", "shivering", "chills", "watering from eyes", "GERD", "stomach pain", "acidity", "ulcers on tongue", "vomiting", "cough", "chest pain", "Chronic cholestasis", "yellowish skin", "nausea", "loss of appetite", "abdominal pain", "yellowing of eyes", "Drug Reaction", "burning micturition", "spotting  urination", "Peptic ulcer diseae", "passage of gases", "internal itching", "indigestion", "AIDS", "muscle wasting", "patches in throat", "high fever", "extra marital contacts", "Diabetes ", "fatigue", "weight loss", "restlessness", "lethargy", "irregular sugar level", "blurred and distorted vision", "obesity", "excessive hunger", "increased appetite", "polyuria", "Gastroenteritis", "sunken eyes", "dehydration", "diarrhoea", "Bronchial Asthma", "breathlessness", "family history", "mucoid sputum", "Hypertension ", "headache", "dizziness", "loss of balance", "lack of concentration", "Migraine", "stiff neck", "depression", "irritability", "visual disturbances", "Cervical spondylosis", "back pain", "weakness in limbs", "neck pain", "Paralysis (brain hemorrhage)", "weakness of one body side", "altered sensorium", "Jaundice", "dark urine", "Malaria", "sweating", "muscle pain", "Chicken pox", "mild fever", "swelled lymph nodes", "malaise", "red spots over body", "Dengue", "joint pain", "pain behind the eyes", "Typhoid", "constipation", "toxic look (typhos)", "belly pain", "hepatitis A", "Hepatitis B", "yellow urine", "receiving blood transfusion", "receiving unsterile injections", "Hepatitis C", "Hepatitis D", "Hepatitis E", "coma", "stomach bleeding", "acute liver failure", "Alcoholic hepatitis", "swelling of stomach", "distention of abdomen", "history of alcohol consumption", "fluid overload", "Tuberculosis", "phlegm", "blood in sputum", "Common Cold", "throat irritation", "redness of eyes", "sinus pressure", "runny nose", "congestion", "loss of smell", "Pneumonia", "fast heart rate", "rusty sputum", "Dimorphic hemmorhoids(piles)", "pain during bowel movements", "pain in anal region", "bloody stool", "irritation in anus", "Heart attack", "Varicose veins", "cramps", "bruising", "swollen legs", "swollen blood vessels", "prominent veins on calf", "Hypothyroidism", "weight gain", "cold hands and feets", "mood swings", "puffy face and eyes", "enlarged thyroid", "brittle nails", "swollen extremeties", "abnormal menstruation", "Hyperthyroidism", "muscle weakness", "Hypoglycemia", "anxiety", "slurred speech", "palpitations", "drying and tingling lips", "Osteoarthristis", "knee pain", "hip joint pain", "swelling joints", "painful walking", "Arthritis", "movement stiffness", "(vertigo) Paroymsal  Positional Vertigo", "spinning movements", "unsteadiness", "Acne", "pus filled pimples", "blackheads", "scurring", "Urinary tract infection", "bladder discomfort", "foul smell of urine", "continuous feel of urine", "Psoriasis", "skin peeling", "silver like dusting", "small dents in nails", "inflammatory nails", "Impetigo", "blister", "red sore around nose", "yellow crust ooze"};
    Button btn;
    List<String> symptom=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        tableLayout=findViewById(R.id.prefer);
        layoutInflater=getLayoutInflater();
        atv=findViewById(R.id.atv);
        scrollView = findViewById(R.id.scr);
        status = findViewById(R.id.prefstatus);
        status.setText("Hide");
        status.setOnClickListener(this);
        atv.setAdapter(new ArrayAdapter<>(Assessment.this, android.R.layout.simple_list_item_1, d));
        atv.setThreshold(0);
        atv.setOnItemClickListener(this);
        btn=findViewById(R.id.detect);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if(v==status){
            if(status.getText().toString().equals("Hide")){
                status.setText("Show");
                scrollView.setVisibility(View.GONE);
            }
            else if(status.getText().toString().equals("Show")){
                status.setText("Hide");
                scrollView.setVisibility(View.VISIBLE);
            }
        }
        if(v==btn){
            //TODO POST REQUEST
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        atv.setText("");
        final View v=layoutInflater.inflate(R.layout.tab,null);
        pref = v.findViewById(R.id.text);
        pref.setText(item);
        symptom.add(item);
        tableLayout.addView(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setVisibility(View.GONE);
            }
        });
    }
}