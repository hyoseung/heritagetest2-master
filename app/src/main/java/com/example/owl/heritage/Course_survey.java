package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;

/**
 * Created by hyoseung on 2016-09-12.
 */
public class Course_survey extends AppCompatActivity {
    private Context mContext=this;
    private Cursor cur;
    private Tab_1 tab1;
    private View v;

    private Button select;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_survey);

        select = (Button) findViewById(R.id.select_button);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("코스이름");
                alert.setMessage("이름을 입력해주세요");

                final EditText input = new EditText(mContext);
                alert.setView(input);

                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        value.toString();

                    }
                });

                alert.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        });

                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

    }
}


