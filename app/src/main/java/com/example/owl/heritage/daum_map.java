package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;


// Created by owl on 2016-09-05.

public class daum_map extends AppCompatActivity implements MapView.MapViewEventListener,MapView.CurrentLocationEventListener, MapView.POIItemEventListener {

    private static final String DAUM_API_KEY = "97f41014e36ff8e1707666e7be5fa51e";
    private Context mContext = this;
    private Heritage_GPS_DB heritage_gps_db;
    private ArrayList<String> Lati;
    private ArrayList<String> Long;
    private MapPOIItem mapPOIItem;
    private ArrayList<String> heritage_list; //독립운동 문화재 이름;
    private Boolean check =false;


    private ArrayList<String> heritage = new ArrayList<String>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.daum_map_content);
        checkGpsService();
        Intent intent = getIntent();

        heritage.add(intent.getStringExtra("heritage1"));
        heritage.add(intent.getStringExtra("heritage2"));
        heritage.add(intent.getStringExtra("heritage3"));
        heritage.add(intent.getStringExtra("heritage4"));


        final MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey(DAUM_API_KEY);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.current_point);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(compoundButton.getId() == R.id.current_point){
                    if(isChecked){
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
                        Toast.makeText(getApplicationContext(),"50m정도의 차이가 날 수 있습니다.",Toast.LENGTH_SHORT).show();
                        check = true;
                    }
                    else{
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                        mapView.setShowCurrentLocationMarker(false);
                        check = false;
                    }
                }
            }
        });

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.map_view);
        viewGroup.addView(mapView);
        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);

    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        heritage_gps_db = new Heritage_GPS_DB(mContext);
        heritage_gps_db.setHeritage(heritage);

        Lati = heritage_gps_db.getLatitude();
        Long = heritage_gps_db.getLongitude();
        heritage_list = heritage;
        for(int i =0 ; i<heritage_list.size();i++) {
            mapPOIItem = new MapPOIItem();
            mapPOIItem.setItemName(heritage_list.get(i));
            mapPOIItem.setTag(0);
            mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati.get(i)), Double.parseDouble(Long.get(i))));
            mapPOIItem.setShowAnimationType(MapPOIItem.ShowAnimationType.SpringFromGround);
            mapPOIItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            mapPOIItem.setCustomImageResourceId(R.drawable.marker1);
            mapPOIItem.setCustomImageAutoscale(false);
            mapPOIItem.setCustomImageAnchor(0.5f,0.5f);
            mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
            mapPOIItem.setCustomSelectedImageResourceId(R.drawable.marker3);
            mapView.addPOIItem(mapPOIItem);
        }
        mapPOIItem.setDraggable(true);
        mapView.fitMapViewAreaToShowAllPOIItems();
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {}
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
    }
    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {}
    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {}
    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {}
    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {}
    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {}
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {}
    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {}
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {}
    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {}
    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {}

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        if(mapPOIItem.getItemName() == heritage_list.get(0))
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati.get(0)), Double.parseDouble(Long.get(0))),2,true);
        if(mapPOIItem.getItemName() == heritage_list.get(1))
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati.get(1)), Double.parseDouble(Long.get(1))),2,true);
        if(mapPOIItem.getItemName() == heritage_list.get(2))
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati.get(2)), Double.parseDouble(Long.get(2))),2,true);
        if(mapPOIItem.getItemName() == heritage_list.get(3))
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati.get(3)), Double.parseDouble(Long.get(3))),2,true);



    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
    private boolean checkGpsService(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //String gps = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        //if(!(gps.matches(".*gps.*") && gps.matches(".*network.*"))){
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //gps off일 때
            AlertDialog.Builder gpsdialog = new AlertDialog.Builder(this);
            gpsdialog.setTitle("위치 서비스 설정");
            gpsdialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다 \n 위치 서비스 기능을 설정하시겠습니까?");
            gpsdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //GPS 설정창으로 이동
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create().show();
            return false;
        }
        else{
            return true;
        }
    }
}

