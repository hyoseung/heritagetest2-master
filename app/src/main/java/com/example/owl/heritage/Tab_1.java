package com.example.owl.heritage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


/**
 * Created by owl on 2016-08-25.
 */
public class Tab_1 extends Fragment implements View.OnClickListener{

    private Context mContext;
    private View v;
    private Button button1 ,add_course;
    private ListView survey_list;

    private Heritage_DB heritage_db;


    public Tab_1(Context mContext) {
        // Required empty public constructor
        this.mContext = mContext;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.tab_1, container, false);

        button1 = (Button) v.findViewById(R.id.map);
        button1.setOnClickListener(this);

        add_course=(Button) v.findViewById(R.id.addCourse);         //설문지 추가버튼
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent survey_page = new Intent(mContext, Course_survey.class);
                startActivity(survey_page);
            }
        });


        survey_list = (ListView)v.findViewById(R.id.courselistView);

        heritage_db = new Heritage_DB(mContext);
        //heritage_db.Choice_query(choice_Lv, view, this);

        //listViewCursorAdapter = heritage_db.getListViewCursorAdapter();


        return  v;

    }


    public void onClick(View V){

        Intent myIntent = new Intent(mContext,daum_map.class);

        startActivity(myIntent);
    }

}

