package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hyoseung on 2016-09-12.
 */
public class ListViewCursorAdapter extends CursorAdapter {
    private Context mContext;
    private Cursor cur;
    private Tab_3 tab3;

    public ListViewCursorAdapter(Context mContext, Cursor cur, Tab_3 tab3) {
        super(mContext, cur, true);
        this.mContext = mContext;
        this.cur = cur;
        this.tab3 = tab3;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listview_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(cur.getString(cur.getColumnIndex("Name")));

        Button checkButton = (Button) view.findViewById(R.id.checkButton);
        checkButton.setTag(cur.getString(1));

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
    }
}
