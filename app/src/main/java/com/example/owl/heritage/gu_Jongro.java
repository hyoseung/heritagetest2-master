package com.example.owl.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyoseung on 2016-09-03.
 * 종로구
 */
public class gu_Jongro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gu_jongro_content);
    }

    //경복궁
    public void button1Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button1);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    //창덕궁
    public void button2Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button2);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    //창경궁
    public void button3Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button3);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    //서울 탑골공원
    public void button5Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button4);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    // 서울 한양도성
    public void button6Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button5);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }


    // 서울 문묘와 성균관
    public void button9Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button6);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    // 서울 중앙고등학교 본관
    public void button10Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button7);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    //종묘
    public void button4Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button8);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

}
