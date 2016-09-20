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
    private Context mContext;// = getActivity().getApplicationContext();
    private ListViewCursorAdapter listViewCursorAdapter;

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

        heritage_db = new Heritage_DB(mContext);
        heritage_db.Choice_query(choice_Lv, view, this);

        listViewCursorAdapter = heritage_db.getListViewCursorAdapter();

        return view;
    }
}