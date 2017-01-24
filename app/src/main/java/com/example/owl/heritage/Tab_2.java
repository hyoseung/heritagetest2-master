package com.example.owl.heritage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by owl on 2016-08-25.
 */
public class Tab_2 extends Fragment implements View.OnClickListener{

    private Context mContext;
    private View v;

    Button query_btn;
    Heritage_find finder;
    AutoCompleteTextView autoText;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    String[] heritage_list;
    private Map_image map_image;

    Button gu_JongroB,gu_DobongNowonB, gu_EunpyeonSeodaemunB, gu_GangbukSeongbukB, gu_GangdongSongpaB,
           gu_GangYangGuB, gu_JungDongSeongGwangB, gu_JungguB, gu_MapoYongsanB, gu_SeochoGangnamB, gu_YongDongGwanKumB;

    public Tab_2(Context mContext) {
        this.mContext = mContext;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.tab_2, container, false);
        finder = new Heritage_find(mContext);
        Button map_view = (Button) v.findViewById(R.id.seoulmap);
        map_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_image = new Map_image(mContext);
                map_image.show();
            }
        });
        query_btn = (Button) v.findViewById(R.id.query);
        query_btn.setOnClickListener(this);

        gu_JongroB = (Button) v.findViewById(R.id.종로구);
        gu_JungguB = (Button) v.findViewById(R.id.중구);
        gu_DobongNowonB = (Button) v.findViewById(R.id.도봉노원);
        gu_EunpyeonSeodaemunB = (Button) v.findViewById(R.id.은평서대문구);
        gu_GangdongSongpaB = (Button) v.findViewById(R.id.강동송파);
        gu_GangbukSeongbukB = (Button) v.findViewById(R.id.강북성북);
        gu_GangYangGuB = (Button) v.findViewById(R.id.강서양천구로);
        gu_JungDongSeongGwangB = (Button) v.findViewById(R.id.중동성광);
        gu_MapoYongsanB=(Button) v.findViewById(R.id.마포용산);
        gu_SeochoGangnamB=(Button) v.findViewById(R.id.서초강남);
        gu_YongDongGwanKumB=(Button) v.findViewById(R.id.영동관금);
        //종로
        gu_JongroB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_Jongro.class);
                startActivity(myIntent);
            }
        });
        //중구
        gu_JungguB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_Junggu.class);
                startActivity(myIntent);
            }
        });
        //도봉노원
        gu_DobongNowonB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_DobongNowon.class);
                startActivity(myIntent);
            }
        });
        //은평서대문
        gu_EunpyeonSeodaemunB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_EunpyeonSeodaemun.class);
                startActivity(myIntent);
            }
        });
        //강북성북
        gu_GangbukSeongbukB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_GangbukSeongbuk.class);
                startActivity(myIntent);
            }
        });
        //강동송파
        gu_GangdongSongpaB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_GangdongSongpa.class);
                startActivity(myIntent);
            }
        });
        //강서양천구로
        gu_GangYangGuB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_GangYangGu.class);
                startActivity(myIntent);
            }
        });
        //중랑동대문성동광진
        gu_JungDongSeongGwangB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_JungDongSeoungGwang.class);
                startActivity(myIntent);
            }
        });
        //마포용산
        gu_MapoYongsanB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_MapoYongsan.class);
                startActivity(myIntent);
            }
        });
        ///서초강남
        gu_SeochoGangnamB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_SeochoGangnam.class);
                startActivity(myIntent);
            }
        });
        //영등포동작관악금천
        gu_YongDongGwanKumB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, gu_YongDongGwanKum.class);
                startActivity(myIntent);
            }
        });

        heritage_list = getResources().getStringArray(R.array.dataArray); //xml파일 가져오는 부분
        adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_dropdown_item_1line, heritage_list);//autoCompleteTextView아래로 보여지는 부분분
        autoText= (AutoCompleteTextView)v.findViewById(R.id.search_heritage);
        autoText.setAdapter(adapter); //adapter로 넘겨줌

        //카메라 지도 관련 권한
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1340);
            return v;
        }

        return  v;
    }

    public void onClick(View V){
        boolean s;

        autoText = (AutoCompleteTextView) v.findViewById(R.id.search_heritage);
        String search_name = autoText.getText().toString();
        s=finder.select_name(search_name);  //검색어 찾는 곳으로 넘겨줌


        if(s==true){ //true 리턴되면 Heritage_informaion으로 넘어감
            Intent myIntent = new Intent(mContext,Heritage_information.class);
            myIntent.putExtra("name", search_name);
            startActivity(myIntent);

        }
        else if(s==false){
            final Toast toast=Toast.makeText(getActivity(),"결과가 없습니다.",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,400);
            toast.show();
        }

    }
}