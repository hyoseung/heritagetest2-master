package com.example.owl.heritage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by owl on 2016-10-07.
 */

public class onepin_map extends AppCompatActivity implements MapView.MapViewEventListener, MapView.CurrentLocationEventListener,MapView.POIItemEventListener{
    private static final String DAUM_API_KEY = "97f41014e36ff8e1707666e7be5fa51e";
    private MapPOIItem mapPOIItem;
    private String select_name;
    private Heritage_GPS_DB heritage_gps_db;
    private Context mContext = this;
    private String Lati ;
    private String Long;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.daum_map_content);
        Intent intent = getIntent();
        select_name = intent.getStringExtra("name");
        checkGpsService();
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

                    }
                    else{
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                        mapView.setShowCurrentLocationMarker(false);

                    }
                }
            }
        });

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.map_view);
        viewGroup.addView(mapView);
        mapView.setMapViewEventListener(this);


    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

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

    @Override
    public void onMapViewInitialized(MapView mapView) {
        heritage_gps_db = new Heritage_GPS_DB(mContext);
        heritage_gps_db.setSelectname(select_name);
        Lati = heritage_gps_db.getlatitude();
        Long = heritage_gps_db.getlongitude();
        mapPOIItem = new MapPOIItem();
        mapPOIItem.setItemName(select_name);
        mapPOIItem.setTag(0);
        mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati), Double.parseDouble(Long)));
        mapPOIItem.setShowAnimationType(MapPOIItem.ShowAnimationType.SpringFromGround);
        mapPOIItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mapPOIItem.setCustomImageResourceId(R.drawable.marker1);
        mapPOIItem.setCustomImageAutoscale(false);
        mapPOIItem.setCustomImageAnchor(0.5f,0.5f);
        mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
        mapPOIItem.setCustomSelectedImageResourceId(R.drawable.marker3);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati), Double.parseDouble(Long)),4,true);
        mapView.addPOIItem(mapPOIItem);
        mapPOIItem.setDraggable(true);
        //mapView.fitMapViewAreaToShowAllPOIItems();
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

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        if(mapPOIItem.getItemName() == select_name)
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(Lati), Double.parseDouble(Long)),2,true);
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

}
