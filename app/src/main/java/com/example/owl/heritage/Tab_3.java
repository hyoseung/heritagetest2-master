package com.example.owl.heritage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by owl on 2016-08-25.
 */
public class Tab_3 extends Fragment {

    private View view;
    private ListView choice_Lv;
    private Heritage_DB heritage_db;
    private Context mContext;
    private Tab3_Adapter customAdapter;

    public Tab_3(Context mContext) {
        // Required empty public constructor
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_3, container, false);
        choice_Lv = (ListView) view.findViewById(R.id.listView);

        customAdapter = new Tab3_Adapter(this); //어댑터 생성
        heritage_db = new Heritage_DB(mContext); //어댑터에 추가하기 위해 heritage_db 생성

        choice_Lv.setAdapter(customAdapter);
        heritage_db.choiceList(customAdapter); //어댑터에 정보 add하기

        return view;
    }
}