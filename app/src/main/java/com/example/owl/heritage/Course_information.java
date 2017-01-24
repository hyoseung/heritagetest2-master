package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hyoseung on 2016-09-26.
 */
public class Course_information extends AppCompatActivity {

    private Context mContext = this;
    private ListView course_Lv;
    private Course_infoAdapter adapter;
    private ArrayList<Course_item> list;

    private Button mapButton;
    private Course_DB course_db;

    private String course_name;
    private Course_image camera_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_view);
        Intent intent = getIntent();
        course_name = intent.getStringExtra("name");
        setTitle(course_name);

        list = new ArrayList<Course_item>();
        dataSetting();

        course_Lv = (ListView) findViewById(R.id.course_listView);
        adapter = new Course_infoAdapter(list);
        course_Lv.setAdapter(adapter);

        course_Lv.setOnItemClickListener(course_click);


        mapButton = (Button) findViewById(R.id.mapButton); //경로보기
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNetworkService()==false) return;
                else {
                    Intent myIntent = new Intent(mContext, daum_map.class);
                    myIntent.putExtra("heritage1", list.get(0).getName());
                    myIntent.putExtra("heritage2", list.get(1).getName());
                    myIntent.putExtra("heritage3", list.get(2).getName());
                    myIntent.putExtra("heritage4", list.get(3).getName());

                    startActivity(myIntent);
                }
            }
        });
    }
    private boolean checkNetworkService() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo moblie = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(!moblie.isConnected() && !wifi.isConnected()){
            //gps off일 때
            AlertDialog.Builder gpsdialog = new AlertDialog.Builder(this);
            gpsdialog.setTitle("무선 네트워크 설정");
            gpsdialog.setMessage("문화재 위치 서비스를 사용하기 위해서는 무선 네트워크 설정이 필요합니다.\n무선 네트워크 서비스 기능을 설정하시겠습니까?");
            gpsdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast toast = Toast.makeText(mContext, "위치서비스를 이용할 수 없습니다.", Toast.LENGTH_SHORT );
                            toast.show();
                            return ;
                        }
                    }).create().show();
            return false;
        } else {
            return true;
        }
    }

    //data setting
    private void dataSetting() {
        course_db = new Course_DB(getApplicationContext(), "COURSE.db", null, 1);
        list = course_db.select(course_name, list);
    }

    public AdapterView.OnItemLongClickListener course_longclick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            onPopupListClick(view, position);
            return false;
        }
    };

    public AdapterView.OnItemClickListener course_click = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onPopupListClick(view, position);
        }
    };

    public void onPopupListClick(final View view, final int num) {
        PopupMenu popup = new PopupMenu(mContext, view);
        popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.item_camera:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, num);
                        break;

                    case R.id.item_heritage:
                        intent = new Intent(mContext, Heritage_information.class);
                        intent.putExtra("name", list.get(num).getName());
                        mContext.startActivity(intent);
                        break;

                    case R.id.item_image:
                        if((list.get(num).getUri()).equals("null")){
                            Toast toast = Toast.makeText(mContext, "사진을 먼저 찍어주세요", Toast.LENGTH_SHORT );
                            toast.show();
                        }
                        else {
                            camera_image = new Course_image(mContext, list.get(num).getUri());
                            camera_image.show();
                        }
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            String string = imgUri.toString();

            list.get(requestCode).setUri(string);
            course_db.update_imagPath(requestCode, list.get(requestCode).getName(), string);


            adapter = new Course_infoAdapter(list);
            course_Lv.setAdapter(adapter);
        }
    }
}