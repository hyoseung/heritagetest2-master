package com.example.owl.heritage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;


// Created by owl on 2016-09-05.

public class daum_map extends AppCompatActivity implements MapView.MapViewEventListener,MapView.CurrentLocationEventListener {

    private static final String DAUM_API_KEY = "97f41014e36ff8e1707666e7be5fa51e";
    private MapPOIItem mapPOIItem;
    private ArrayList<MapPOIItem> arrayList;
    private double [] arr1 = new double[4];
    private double [] arr2 = new double[4];
    private double lat1 = 37.53737528;
    private double long1 = 127.00557633;
    private double lat2 = 37.6026422;
    private double long2 = 126.9530633;
    private double lat3 = 37.6108733;
    private double long3 = 126.9951006;
    private double lat4 = 37.5882312;
    private double long4 = 126.9914173;
    private Context mContext = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daum_map_content);
        MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey(DAUM_API_KEY);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.map_view);
        viewGroup.addView(mapView);
        mapView.setMapViewEventListener(this);
        arrayList = new ArrayList<>();
        arr1[0] = lat1; arr1[1]= lat2; arr1[2]= lat3; arr1[3]= lat4;
        arr2[0]=long1; arr2[1]= long2; arr2[2]=long3; arr2[3]= long4;
    }
    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        MapPoint.GeoCoordinate mapPointGeo = mapView.getMapCenterPoint().getMapPointGeoCoord();
        Log.i("좌표", String.format("MapView onCurrentLocationUpdate (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
        for(int i=0; i<4; i++) {
            mapPOIItem = new MapPOIItem();
            mapPOIItem.setItemName("default");
            mapPOIItem.setTag(0);
            mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(arr1[i], arr2[i]));
            Log.i("마커",String.format("new map(%f,%f),",arr1[i],arr2[i]));
            mapPOIItem.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
            mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mapView.addPOIItem(mapPOIItem);
        }


    }
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {


    }


    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
}

