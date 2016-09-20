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
 * Created by hyoseung on 2016-09-20.
 */
public class Tab3_Adapter extends BaseAdapter {

    private ArrayList<String> list;
    private Tab_3 tab3;

    public Tab3_Adapter(Tab_3 tab3){
        list = new ArrayList<String>();
        this.tab3 = tab3;
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

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);

            TextView text = (TextView) convertView.findViewById(R.id.name);
            text.setText(list.get(position));
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, Heritage_information.class);
                myIntent.putExtra("name", list.get(pos));
                mContext.startActivity(myIntent);
            }

        });

        Button checkButton = (Button) convertView.findViewById(R.id.checkButton);
        checkButton.setTag(list.get(pos));

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view;
                view = v;

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("즐겨찾기");

                alertDialogBuilder.setMessage("즐겨찾기에서 삭제하시겠습니까?");
                alertDialogBuilder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tagValue = (String) view.getTag();
                        Check_update update = new Check_update(mContext, tagValue);
                        update.Check_false();
                        FragmentTransaction ft = tab3.getFragmentManager().beginTransaction();
                        ft.detach(tab3).attach(tab3).commit();
                    }
                });
                alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();

            }
        });

        return convertView;
    }

    public void add(String msg){
        list.add(msg);
    }
}

