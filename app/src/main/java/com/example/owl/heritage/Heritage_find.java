package com.example.owl.heritage;

/**
 * Created by owl on 2016-09-12.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by hyoseung on 2016-08-28.
 */
public class Heritage_find extends AppCompatActivity {
    private Context mContext = this;
    private String DB_NAME = "Test.db";
    private String TABLE_NAME = "information";
    private SQLiteDatabase db;
    private Heritage_DB heritage_db;
    private Cursor cur;

    public Heritage_find(Context mContext) {

        this.mContext = mContext;

       heritage_db.initialize(mContext);

        db = mContext.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    public boolean select_name(String search_name) { //검색어 찾아주는 메소드
        cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Name='" + search_name + "'", null);

        if (cur.getCount() > 0)
            return true;
        else if (cur.getCount() == 0)
            return false;
        cur.close();
        db.close();
        return false;
    }

}