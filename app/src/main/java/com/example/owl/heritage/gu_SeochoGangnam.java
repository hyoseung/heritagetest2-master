package com.example.owl.heritage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hyoseung on 2016-09-03.
 * 서초 강남
 */
public class gu_SeochoGangnam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gu_seochogangnam_content);
    }

    //도산공원
    public void button1Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button1);
        myIntent.putExtra("name", btn.getText());
        //myIntent.putExtra("name", "수유동 분청사기 가마터"); //만약 버튼에 text없이 이미지만 넣을 경우
        startActivity(myIntent);
    }

    // 봉은사
    public void button2Click(View v){
        Intent myIntent = new Intent(getApplicationContext(), Heritage_information.class);
        Button btn = (Button)findViewById(R.id.button2);
        myIntent.putExtra("name", btn.getText());
        //myIntent.putExtra("name", "수유동 분청사기 가마터"); //만약 버튼에 text없이 이미지만 넣을 경우
        startActivity(myIntent);
    }
}
