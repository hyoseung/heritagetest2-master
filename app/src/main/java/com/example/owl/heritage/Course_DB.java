package com.example.owl.heritage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by owl on 2016-09-21.
 */
public class Course_DB extends SQLiteOpenHelper{

    private Context mContext;
    private String theme;
    private Cursor cursor;
    private SQLiteDatabase db;
    private String DB_NAME = "Test.db";

    public Course_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    public Course_DB(Context mContext){
        super(mContext,"Test.db",null,1);
        this.mContext = mContext;

        db = mContext.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE COURSE_DB(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT," +
                "heritage1 TEXT,heritage1_image TEXT," +
                "heritage2 TEXT,heritage2_image TEXT,"+
                "heritage3 TEXT,heritage3_image TEXT," +
                "heritage4 TEXT,heritage4_image TEXT,theme TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void insert(String _query){
        db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public ArrayList<Course_item>  select(String value, ArrayList<Course_item> items){
        db = getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM COURSE_DB WHERE name='"+value+"'", null);
        cursor.moveToFirst();

        Log.i("디비 1","name="+cursor.getString(2)+" / 이미지경로="+cursor.getString(3));
        Log.i("디비 2","name="+cursor.getString(4)+" / 이미지경로="+cursor.getString(5));
        Log.i("디비 3","name="+cursor.getString(6)+" / 이미지경로="+cursor.getString(7));
        Log.i("디비 4","name="+cursor.getString(8)+" / 이미지경로="+cursor.getString(9));

        items.add(new Course_item(1,cursor.getString(2), cursor.getString(3)));
        items.add(new Course_item(2, cursor.getString(4), cursor.getString(5)));
        items.add(new Course_item(3,cursor.getString(6),cursor.getString(7)));
        items.add(new Course_item(4, cursor.getString(8), cursor.getString(9)));

        theme = cursor.getString(10);

        cursor.close();
        return items;
    }

    public boolean check_coursename(String inputname){
        db = getReadableDatabase();

        cursor = db.rawQuery("select * from COURSE_DB where name='"+inputname+"'",null);

        if(cursor.getCount() == 0) {
            cursor.close();
            return true;
        }

        cursor.close();
        return false;
    }

    public void update_imagPath(int num, String name, String path){
        Log.i("이미지 업데이트", "이미지 경로 업데이트 중");
        String sql = null;
        try {
            switch (num) {
                case 0:
                    sql = "UPDATE COURSE_DB SET heritage1_image='" + path + "' WHERE heritage1='" + name + "';";
                    break;
                case 1:
                    sql = "UPDATE COURSE_DB SET heritage2_image='" + path + "' WHERE heritage2='" + name + "';";
                    break;
                case 2:
                    sql = "UPDATE COURSE_DB SET heritage3_image='" + path + "' WHERE heritage3='" + name + "';";
                    break;
                case 3:
                    sql = "UPDATE COURSE_DB SET heritage4_image='" + path + "' WHERE heritage4='" + name + "';";
                    break;
            }
            db.execSQL(sql);

        } catch (Exception e) {
            Log.i("_)error!!!!", "" + e.toString());
        }
    }

    public void Delete_data(String name){
        db = getWritableDatabase();
        db.delete("COURSE_DB","name=?",new String[]{name});
        db.close();;
    }

    public void ChoiceList(Tab1_Adapter tab1_adapter){
        db=getReadableDatabase();
        cursor = db.rawQuery("select * from COURSE_DB",null);
        if(cursor.getCount() == 0) return;
        while (cursor.moveToNext())
            tab1_adapter.add(cursor.getString(1));
        cursor.close();
        db.close();
    }
}


