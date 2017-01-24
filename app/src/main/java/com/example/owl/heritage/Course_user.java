package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nari on 2016-09-30.
 */
public class Course_user extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Context mContext = this;
    private Course_DB course_db;
    private ArrayList<String> list = new ArrayList<String>();
    private CheckBox[] checkBox = new CheckBox[26];
    private String theme="user";

    private Button select;
    private Button cancel;
    private int count=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_user);

        datasetting(); //checkbox 등록

        course_db = new Course_DB(getApplicationContext(), "COURSE.db", null, 1);
        select = (Button) findViewById(R.id.select_button_u);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<4){
                    Toast toast = Toast.makeText(mContext, (4-count)+"개를 더 선택하세요.", Toast.LENGTH_SHORT );
                    toast.show();
                }
                else if(count>4){
                    Toast toast = Toast.makeText(mContext, (count-4)+"개를 빼주세요.", Toast.LENGTH_SHORT );
                    toast.show();
                }
                else {
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
                                setHeritage(); //선택한 문화재 arraylist에 담기
                                Log.i("사용자 확정 코스", " " + list.get(0) + " " + list.get(1) + " " + list.get(2) + " " + list.get(3));

                                course_db.insert("insert into COURSE_DB values(null, '" + value + "','" + list.get(0) + "','null','" +
                                        list.get(1) + "','null','" + list.get(2) + "','null','" + list.get(3) + "','null','" + theme + "');");

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
            }
        });

        cancel = (Button) findViewById(R.id.cancel_button_u);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setHeritage(){
        int c=0;
        for(int i=0; i<26; i++){
            if(checkBox[i].isChecked()){
                c++;

                if((checkBox[i].getText().toString()).equals("중앙고 본관"))
                    list.add("중앙고등학교 본관");
                else if((checkBox[i].getText().toString()).equals("연대 언더우드관"))
                    list.add("연세대학교 언더우드관");
                else if((checkBox[i].getText().toString()).equals("벨기에 영사관"))
                    list.add("구 벨기에 영사관");
                else if((checkBox[i].getText().toString()).equals("경성방직"))
                    list.add("구 경성방직 사무동");
                else if((checkBox[i].getText().toString()).equals("백범김구"))
                    list.add("백범 김구 기념관");
                else if((checkBox[i].getText().toString()).equals("가마터"))
                    list.add("수유동 분청사기 가마터");
                else if((checkBox[i].getText().toString()).equals("도봉서원"))
                    list.add("도봉서원과 각석군");
                else
                    list.add(checkBox[i].getText().toString());
            }
            if(c==4) break;
        }
    }

    public void datasetting(){
        checkBox[0]=(CheckBox)findViewById(R.id.checkBox1);
        checkBox[1]=(CheckBox)findViewById(R.id.checkBox2);
        checkBox[2]=(CheckBox)findViewById(R.id.checkBox3);
        checkBox[3]=(CheckBox)findViewById(R.id.checkBox4);
        checkBox[4]=(CheckBox)findViewById(R.id.checkBox5);
        checkBox[5]=(CheckBox)findViewById(R.id.checkBox6);
        checkBox[6]=(CheckBox)findViewById(R.id.checkBox7);
        checkBox[7]=(CheckBox)findViewById(R.id.checkBox8);
        checkBox[8]=(CheckBox)findViewById(R.id.checkBox9);
        checkBox[9]=(CheckBox)findViewById(R.id.checkBox10);

        checkBox[10]=(CheckBox)findViewById(R.id.checkBox11);
        checkBox[11]=(CheckBox)findViewById(R.id.checkBox12);
        checkBox[12]=(CheckBox)findViewById(R.id.checkBox13);
        checkBox[13]=(CheckBox)findViewById(R.id.checkBox14);
        checkBox[14]=(CheckBox)findViewById(R.id.checkBox15);
        checkBox[15]=(CheckBox)findViewById(R.id.checkBox16);
        checkBox[16]=(CheckBox)findViewById(R.id.checkBox17);
        checkBox[17]=(CheckBox)findViewById(R.id.checkBox18);
        checkBox[18]=(CheckBox)findViewById(R.id.checkBox19);
        checkBox[19]=(CheckBox)findViewById(R.id.checkBox20);

        checkBox[20]=(CheckBox)findViewById(R.id.checkBox21);
        checkBox[21]=(CheckBox)findViewById(R.id.checkBox22);
        checkBox[22]=(CheckBox)findViewById(R.id.checkBox23);
        checkBox[23]=(CheckBox)findViewById(R.id.checkBox24);
        checkBox[24]=(CheckBox)findViewById(R.id.checkBox25);
        checkBox[25]=(CheckBox)findViewById(R.id.checkBox26);

        for(int i=0; i<26; i++)
            checkBox[i].setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.checkBox1:
                if (checkBox[0].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox2:
                if (checkBox[1].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox3:
                if (checkBox[2].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox4:
                if (checkBox[3].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox5:
                if (checkBox[4].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox6:
                if (checkBox[5].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox7:
                if (checkBox[6].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox8:
                if (checkBox[7].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox9:
                if (checkBox[8].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox10:
                if (checkBox[9].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox11:
                if (checkBox[10].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox12:
                if (checkBox[11].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox13:
                if (checkBox[12].isChecked())  count++;
                else count--;
                break;

            case R.id.checkBox14:
                if (checkBox[13].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox15:
                if (checkBox[14].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox16:
                if (checkBox[15].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox17:
                if (checkBox[16].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox18:
                if (checkBox[17].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox19:
                if (checkBox[18].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox20:
                if (checkBox[19].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox21:
                if (checkBox[20].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox22:
                if (checkBox[21].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox23:
                if (checkBox[22].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox24:
                if (checkBox[23].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox25:
                if (checkBox[24].isChecked()) count++;
                else count--;
                break;

            case R.id.checkBox26:
                if (checkBox[25].isChecked()) count++;
                else count--;
                break;
        }
    }
}
