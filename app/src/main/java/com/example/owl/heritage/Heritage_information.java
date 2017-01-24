package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by hyoseung on 2016-08-28.
 */
public class Heritage_information extends AppCompatActivity {
    private Context mContext = this;
    private String image_name, image_name_text;
    private String choice_state;
    private String search_name;
    private Heritage_DB heritage_db;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heritage_view);

        Intent intent = getIntent();
        search_name = intent.getStringExtra("name");
        setTitle(search_name);

        heritage_db = new Heritage_DB(mContext);
        heritage_db.Heritage_query(search_name);

        //이미지 이름 저장
        image_name = heritage_db.getDB_Image();
        image_name_text=heritage_db.getDB_Summary();

        //즐겨찾기 상태
        choice_state = heritage_db.getDB_Choice();

        Button Button_test = (Button) findViewById(R.id.test);
        Button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view;
                view = v;
                AlertDialog.Builder alertDialogBuilder =  new AlertDialog.Builder(mContext);
                alertDialogBuilder.setTitle("운영시간");
                alertDialogBuilder.setMessage(heritage_db.getDB_History());
                alertDialogBuilder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            }
        });
        //맵 버튼
        Button Button_view = (Button) findViewById(R.id.view_map);
        Button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNetworkService()==false) return;
                else {
                    Intent intent = new Intent(getApplicationContext(), onepin_map.class);
                    intent.putExtra("name", search_name);
                    startActivity(intent);
                }
            }
        });
        Button heritage_uri = (Button) findViewById(R.id.uri);
        heritage_uri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_uri = new Intent(Intent.ACTION_VIEW, Uri.parse(heritage_db.getDB_Uri()));
                startActivity(intent_uri);
            }
        });

        //이미지
        int imageResource = mContext.getResources().getIdentifier(image_name, "drawable", "com.example.owl.heritage");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(mContext)
                .load(imageResource)
                .into(imageView);

        int imageResource1 = mContext.getResources().getIdentifier(image_name_text, "drawable", "com.example.owl.heritage");
        ImageView Text_summary = (ImageView) findViewById(R.id.dbsummary);
        Glide.with(mContext)
                .load(imageResource1)
                .into(Text_summary);

        //체크박스
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        if (choice_state.equals("true")) checkBox.setChecked(true); //즐겨찾기 선택
        else checkBox.setChecked(false); //즐겨찾기 선택하지 않음

        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Check_update update = new Check_update(mContext, search_name);
                if (buttonView.getId() == R.id.checkBox) {
                    if (isChecked) {
                        Log.i("option", "즐겨찾기내용에 추가");
                        update.Check_true();
                    } else {
                        Log.i("option", "즐겨찾기내용에 삭제");
                        update.Check_false();
                    }
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Heritage_information Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
