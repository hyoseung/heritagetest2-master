package com.example.owl.heritage;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hyoseung on 2016-08-28.
 */
public class Heritage_information extends AppCompatActivity {
    private Context mContext = this;


    private String DB_NAME = "Test.db";
    private String TABLE_NAME = "information";
    private String image_name;
    private String choice_state;

    private SQLiteDatabase db;


    private String search_name;

    private Heritage_DB heritage_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heritage_view);

        Intent intent = getIntent();
        search_name = intent.getStringExtra("name");
        setTitle(search_name);

        Log.i("select!!!",""+search_name);

        heritage_db = new Heritage_DB(mContext);
        heritage_db.Heritage_query(search_name);

        //이미지 이름 저장
        image_name = heritage_db.getDB_Image();

        //즐겨찾기 상태
        choice_state = heritage_db.getDB_Choice();

        //텍스트 뷰
        TextView Text_name = (TextView) findViewById(R.id.dbname);
        TextView Text_location = (TextView) findViewById(R.id.dblocation);
        TextView Text_summary = (TextView) findViewById(R.id.dbsummary);

        Text_name.append(heritage_db.getDB_Name());
        Text_location.append(heritage_db.getDB_Location());
        //Text_summary.append(heritage_db.getDB_Summary());

        //이미지
        int imageResource = mContext.getResources().getIdentifier(image_name,"drawable","com.example.owl.heritage");
        Bitmap test = BitmapFactory.decodeResource(getResources(),imageResource);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(test);

        //체크박스
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        if (choice_state.equals("true")) checkBox.setChecked(true); //즐겨찾기 선택
        else checkBox.setChecked(false); //즐겨찾기 선택하지 않음

        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Check_update update = new Check_update(mContext, search_name);
                if (buttonView.getId() == R.id.checkBox) {
                    if (isChecked) {
                        Log.i("option", "즐겨찾기내용에 추가");
                        update.Check_true();
                    } else {
                        Log.i("option", "즐겨찾기내용에 삭제");
                        update.Check_false();
                    }
                }
            }
        });
    }
}
