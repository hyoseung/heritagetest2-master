package com.example.owl.heritage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by owl on 2016-08-25.
 */
public class Tab_1 extends Fragment {

    private Context mContext;
    private View v;
    private Button add_course,user_check;
    private ListView survey_list;
    private Tab1_Adapter tab1_adapter;
    private Course_DB course_db;
    private Question_image question_image;



    public Tab_1(Context mContext) {
        // Required empty public constructor
        this.mContext = mContext;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.tab_1, container, false);

        add_course=(Button) v.findViewById(R.id.addCourse);         //설문지 추가버튼
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent survey_page = new Intent(mContext, Course_survey.class);
                startActivityForResult(survey_page,1);
            }
        });
        user_check=(Button) v.findViewById(R.id.usermind);         //설문지 추가버튼
        user_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent course_user = new Intent(mContext, Course_user.class);
                startActivityForResult(course_user,1);
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.question);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_image = new Question_image(mContext);
                question_image.show();
            }
        });

        survey_list = (ListView)v.findViewById(R.id.courselistView);
        //survey_list.setOnTouchListener(new );

        tab1_adapter = new Tab1_Adapter(this); //어뎁터 생성
        course_db = new Course_DB(mContext,"COURSE.db",null,1);

        survey_list.setAdapter(tab1_adapter); // tab1_adapter 추가

        course_db.ChoiceList(tab1_adapter);

        return  v;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

    }


}

