package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hyoseung on 2016-09-12.
 */
public class Course_survey_alone extends AppCompatActivity {
    private Context mContext = this;
    private ArrayList<String> list;
    private String theme;
    private Button select;
    private Button cancel;
    private Course_DB course_db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_survey_alone);

        theme = "걷기";
        list=new ArrayList<String>();
        list.add("도산공원"); list.add("한양도성"); list.add("호암산성"); list.add("아차산성");

        course_db = new Course_DB(getApplicationContext(), "COURSE.db", null, 1);
        select = (Button) findViewById(R.id.select_button_a);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("코스이름");

                final EditText input = new EditText(mContext);
                input.setHint("코스 이름을 입력해주세요");
                alert.setView(input);

                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        if(value.equals("") || value.equals(" ")){
                            Toast toast = Toast.makeText(mContext, "코스이름을 입력해 주세요.", Toast.LENGTH_SHORT );
                            toast.show();
                        }
                        else if(course_db.check_coursename(value) == true) {
                            course_db.insert("insert into COURSE_DB values(null, '" + value + "','"+list.get(0)+"','null','" +
                                    list.get(1)+"','null','"+list.get(2)+"','null','"+list.get(3)+"','null','"+theme+"');");

                            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.toggleSoftInput(0, 0);
                            finish();
                        }
                        else if(course_db.check_coursename(value) == false) {
                            Toast toast = Toast.makeText(mContext, "코스이름이 중복됩니다.", Toast.LENGTH_SHORT );
                            toast.show();
                        }
                    }
                });

                alert.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        cancel = (Button) findViewById(R.id.cancel_button_a);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }

}

