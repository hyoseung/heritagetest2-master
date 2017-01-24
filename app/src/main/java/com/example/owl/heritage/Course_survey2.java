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
public class Course_survey2 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_survey2);
    }

    public void Button_alone(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey_alone.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_friend(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey_friend.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_family(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey_family.class);
        startActivity(myIntent);
        finish();
    }
    public void Button_couple(View v){
        Intent myIntent = new Intent(getApplicationContext(), Course_survey_couple.class);
        startActivity(myIntent);
        finish();
    }
    @Override
    public void onBackPressed() {}
}


