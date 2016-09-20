package com.example.owl.heritage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by owl on 2016-09-09.
 */
public class Intro extends Activity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//인트로 화면이라서 타이틀바 안넣음
        setContentView(R.layout.activity_intro);
        handler = new Handler(); //딜레이를 주기 위해서 핸들러 사용해야돼;;
        handler.postDelayed(run,2000);
    }
    Runnable run = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Intro.this,MainActivity.class);//인트로에서 메인으로 넘어가기 위한 인텐트
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); //overridePendingTransition이란 함수를 이용하여 효과를 줌, 순서가 중요

        }
    };
    //인트로 중에 뒤로가기를 누를 경우 핸들러를 끊어버려 아무일 없게 만드는 부분
    //미 설정시 인트로 중 뒤로가기를 누르면 인트로 후에 홈화면이 나옴.
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(run);
    }

}
