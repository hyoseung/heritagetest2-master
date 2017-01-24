package com.example.owl.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyoseung on 2016-09-03.
 * 중구
 */
public class gu_Junggu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gu_junggu_content);
    }

    //덕수궁
    public void button1Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button1);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    // 서울 명동성당
    public void button5Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button2);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    // 서울 정동교회
    public void button6Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button3);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }

    // 성공회 서울성당
    public void button7Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button4);
        myIntent.putExtra("name", btn.getText());
        startActivity(myIntent);
    }
}
