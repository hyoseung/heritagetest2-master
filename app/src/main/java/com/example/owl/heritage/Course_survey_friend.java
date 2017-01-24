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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hyoseung on 2016-09-12.
 */
public class Course_survey_friend extends AppCompatActivity {
    private Context mContext = this;
    private ArrayList<String> list;
    private String theme;

    private RadioButton palaceB,exprienceB,religionB, walkB,schoolB;
    private Button cancel,select;
    private Course_DB course_db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_survey_friend);

        RadioGroup rd = (RadioGroup) this.findViewById(R.id.group3);
        rd.setOnCheckedChangeListener(optionOnClickListene);

        palaceB=(RadioButton)findViewById(R.id.hanbok);
        exprienceB=(RadioButton)findViewById(R.id.exprience_fr);
        religionB=(RadioButton)findViewById(R.id.religion_fr);
        walkB=(RadioButton)findViewById(R.id.walk);
        schoolB=(RadioButton)findViewById(R.id.school_fr);

        course_db = new Course_DB(getApplicationContext(), "COURSE.db", null, 1);
        select = (Button) findViewById(R.id.select_button_fr);
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

        cancel = (Button) findViewById(R.id.cancel_button_fr);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
    RadioGroup.OnCheckedChangeListener optionOnClickListene = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.hanbok:
                    list = new ArrayList<String>();
                    list.add("경복궁");
                    list.add("덕수궁");
                    list.add("창경궁");
                    list.add("창덕궁");
                    theme = "궁궐";
                    break;

                case R.id.exprience_fr:
                    list = new ArrayList<String>();
                    list.add("종묘");
                    list.add("백범 김구 기념관");
                    list.add("서대문 형무소");
                    list.add("암사동 유적");
                    theme = "독립";
                    break;

                case R.id.religion_fr:
                    list = new ArrayList<String>();
                    list.add("명동성당");
                    list.add("정동교회");
                    list.add("성공회 서울성당");
                    list.add("봉은사");
                    theme = "종교";
                    break;

                case R.id.walk:
                    list = new ArrayList<String>();
                    list.add("도산공원");
                    list.add("한양도성");
                    list.add("호암산성");
                    list.add("아차산성");
                    theme = "걷기";
                    break;

                case R.id.school_fr:
                    list = new ArrayList<String>();
                    list.add("도봉서원과 각석군");
                    list.add("문묘와 성균관");
                    list.add("연세대학교 언더우드관");
                    list.add("중앙고등학교 본관");
                    theme = "학교";
                    break;
            }
        }
    };

}

