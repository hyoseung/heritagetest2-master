package com.example.owl.heritage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyoseung on 2016-09-12.
 */
public class Course_survey extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_survey);

    }
    public void Button_10(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey2.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_20(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey2.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_30(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey2.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_40(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey2.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_dontcare(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey2.class);
        startActivity(myIntent);
        finish();
    }

}


