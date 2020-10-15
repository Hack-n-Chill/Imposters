package com.imposters.yourdoctor;

public class Story {
    public String symptom,medication,disease;
    public String review;
    public Story(String symptom, String d, String medication, String r){
        this.symptom=symptom;
        this.disease=d;
        this.medication=medication;
        this.review=r;
    }
    public Story(){}
}
