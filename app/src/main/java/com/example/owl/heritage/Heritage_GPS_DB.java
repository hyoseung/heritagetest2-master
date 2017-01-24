package com.example.owl.heritage;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by owl on 2016-09-22.
 */
public class Heritage_GPS_DB extends SQLiteOpenHelper{
    private Context mContext;
    private String DB_NAME = "Test.db";
    private String TABLE_NAME = "Heritage_GPS";
    private SQLiteDatabase db;
    private Cursor cursor;
    private String latitude;//문화재 좌표
    private String longitude;//문화재 좌표
    private String name; //문화재 이름
    private ArrayList<String> heritage_list_latitude;
    private ArrayList<String> heritage_list_longitude;
    private ArrayList<String> heritage_list_name; //heritage list를 저장하기 위한
    private ArrayList<String> heritage;

    public Heritage_GPS_DB(Context mContext){
        super(mContext,"Test.db",null,1);
        this.mContext = mContext;
        initialize(mContext);
        db = mContext.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE,null);
    }
    public void setSelectname(String name){
        this.name = name;
    }

    public void setHeritage(ArrayList<String> heritage){
        this.heritage = heritage;
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

    public String getlatitude(){

        db= getReadableDatabase();
        cursor = db.rawQuery("SELECT Latitude FROM "+ TABLE_NAME + " WHERE Name = '"+name+"'",null);
        cursor.moveToFirst();
        latitude = cursor.getString(0);
        cursor.close();
        return latitude;
    }

    public String getlongitude(){

        db= getReadableDatabase();
        cursor = db.rawQuery("SELECT Longitude FROM "+ TABLE_NAME + " WHERE Name = '"+name+"'",null);
        cursor.moveToFirst();
        longitude = cursor.getString(0);
        cursor.close();
        return longitude;
    }




    public ArrayList<String> getLatitude(){     //각 문화재에 해당하는 위도를 출력
        heritage_list_name = new ArrayList<String>() ;
        heritage_list_name=heritage; //heritage_list_name에 문화재 이름 저장
        heritage_list_latitude = new ArrayList<String>(); //위도를 저장할 리스트

        db = getReadableDatabase();
        for(int i=0; i<heritage_list_name.size();i++) {
            cursor = db.rawQuery("SELECT Latitude FROM " + TABLE_NAME + " WHERE Name = '" + heritage_list_name.get(i) + "'", null);
            cursor.moveToFirst();
            heritage_list_latitude.add(cursor.getString(0));
        }
        cursor.close();
        return heritage_list_latitude;
    }

    public ArrayList<String> getLongitude(){   //각 문화재에 해당하는 경도를 출력
        heritage_list_name = new ArrayList<String>() ;
        heritage_list_name=heritage;
        heritage_list_longitude = new ArrayList<String>();

        db = getReadableDatabase();
        for(int i=0; i<heritage_list_name.size();i++) {
            cursor = db.rawQuery("SELECT Longitude FROM " + TABLE_NAME + " WHERE Name = '" + heritage_list_name.get(i) + "'", null);
            cursor.moveToFirst();
            heritage_list_longitude.add(cursor.getString(0));
        }
        cursor.close();
        return heritage_list_longitude;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
