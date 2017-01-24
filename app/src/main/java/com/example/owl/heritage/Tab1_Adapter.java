package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by owl on 2016-09-22.
 */
public class Tab1_Adapter extends BaseAdapter{

    private ArrayList<String> list;
    private Tab_1 tab_1;

    public Tab1_Adapter(Tab_1 tab_1){
        list = new ArrayList<String>();
        this.tab_1 = tab_1;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context mContext = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(R.layout.listview_item,parent,false);
            TextView textView = (TextView) convertView.findViewById(R.id.name);
            textView.setText(list.get(position));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Course_information.class);
                intent.putExtra("name",list.get(pos));
                mContext.startActivity(intent);
            }
        });
        Button checkButton = (Button) convertView.findViewById(R.id.checkButton);
        checkButton.setTag(list.get(pos));

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view;
                view = v;
                AlertDialog.Builder alertDialogBuilder =  new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("나만의 코스");
                alertDialogBuilder.setMessage("코스를 삭제하시겠습니까?");
                alertDialogBuilder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tagValue = (String) view.getTag();
                        Course_DB course_db = new Course_DB(mContext,"COURSE.db",null,1);
                        course_db.Delete_data(tagValue);
                        FragmentTransaction ft = tab_1.getFragmentManager().beginTransaction();
                        ft.detach(tab_1).attach(tab_1).commit();
                    }
                });
                alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            }
        });
        return convertView;
    }
    public  void add(String msg){
        list.add(msg);
    }
}
