package com.example.owl.heritage;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by hyoseung on 2016-09-04.
 */
public class Heritage_DB extends SQLiteOpenHelper {
    private Context mContext;
    private String DB_NAME = "Test.db";
    private String TABLE_NAME = "information";
    private SQLiteDatabase db;
    private Cursor cur;

    private String heritage_name;

    private String DB_Name;      //1
    private String DB_Location; //2
    private String DB_Summary;  //3
    private String DB_Image;    //4
    private String DB_Choice;   //5

    private ListViewCursorAdapter listViewCursorAdapter;
    private ListView choice_Lv;
    private View view;

    public Heritage_DB(Context mContext) {
        super(mContext, "Test.db", null, 1);

        this.mContext = mContext;

        initialize(mContext);

        db = mContext.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    //data/data/databases에 있는지 확인 없으면 복사
    public static void initialize(Context mContext) {
        File folder = new File("/data/data/com.example.owl.heritage/databases");
        folder.mkdirs();
        File outfile = new File("/data/data/com.example.owl.heritage/databases/Test.db");

        if (outfile.length() <= 0) {
            AssetManager assetManager = mContext.getResources().getAssets();
            try {
                InputStream is = assetManager.open("db/Test.db", AssetManager.ACCESS_BUFFER);
                long filesize = is.available();
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                FileOutputStream fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            } catch (Exception e) {
            }
        }
    }

    //Heritage_information.java
    public void Heritage_query(String heritage_name) {
        this.heritage_name = heritage_name;
        cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Name='" + heritage_name + "'", null);

        cur.moveToFirst();

        Log.i("move!!!", "" + cur.getString(0));
        Log.i("move!!!", "" + cur.getString(1));
        Log.i("move!!!", "" + cur.getString(2));
        Log.i("move!!!", "" + cur.getString(3));
        Log.i("move!!!", "" + cur.getString(4));
        Log.i("move!!!", "" + cur.getString(5));

        DB_Name = cur.getString(1);
        DB_Location = cur.getString(2);
        DB_Summary = cur.getString(3);
        DB_Image = cur.getString(4);
        DB_Choice = cur.getString(5);

        cur.close();
    }

    public String getDB_Name() {
        return DB_Name;
    }

    public String getDB_Location() {
        return DB_Location;
    }

    public String getDB_Summary() {
        return DB_Summary;
    }

    public String getDB_Image() {
        return DB_Image;
    }

    public String getDB_Choice() {
        return DB_Choice;
    }


    //Tab3.java
    public void Choice_query(final ListView choice_Lv, View view, Tab_3 tab3) {
        this.choice_Lv = choice_Lv;
        this.view = view;

        db = getWritableDatabase();

        cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Choice='true'", null);

        cur.moveToFirst();

        if (cur.getCount() == 0) return;

        //내용이 있을경우
        listViewCursorAdapter = new ListViewCursorAdapter(mContext, cur, tab3);
        choice_Lv.setAdapter(listViewCursorAdapter);

        db.close();

        choice_Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cur.moveToPosition(position);
                String name = cur.getString(1);

                Intent myIntent = new Intent(mContext, Heritage_information.class);
                myIntent.putExtra("name", name);
                mContext.startActivity(myIntent);
            }
        });
    }

    public ListViewCursorAdapter getListViewCursorAdapter() {
        return listViewCursorAdapter;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
