package com.example.owl.heritage;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

public class GpsInfo extends Service implements LocationListener {
    protected LocationManager locationM;
    private final Context mContext;
    private static final double area = 0.00038; // GPS 반경을 구할때
    boolean isGPSEnabled = false; // 현재 GPS 사용 유무
    boolean isNetworkEnabled = false; // 네트워크 사용 유무
    boolean isGetlocation = false; // GPS 상태값

    Location location;
    // 위도 경도
    double lat;
    double lon;
    // 최소 GPS 정보 업데이트 거리 3미터
    // private static final long MIN_DISTANCE_CHANGE_FOR_UPDATE = 3;
    // 가만히 서 있어도 GPS 정보 업데이트(거리 0미터)
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATE = 0;
    // 최소 GPS 정보 업데이트 시간(밀리세컨이므로 3분)
    // private static final long MIN_TIME_BW_UPDATES = 1000 * 180 * 1;
    // 테스트 1초
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1 * 1;

    public GpsInfo(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        locationM = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        // GPS 정보 가져오기
        isGPSEnabled = locationM.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 현재 네트워크 상태값 알아오기
        isNetworkEnabled = locationM.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
            // 네트워크와 GPS 사용이 불가능할 때
        } else {
            this.isGetlocation = true;
            // 네트워크 정보로부터 위치값 가져오기
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return location;
                }
                locationM.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATE, this);

                if (locationM != null) {
                    location = locationM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if (location != null) {
                        // 위도 경도 저장
                        lat = location.getLatitude();
                        lon = location.getLongitude();
                    }
                }

            }

            if (isGPSEnabled) {
                if (location == null) {
                    locationM.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATE, this);

                    if (locationM != null) {
                        location = locationM.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }
                    }
                }
            }
        }

        return location;

    }

    // GPS 종료
    public void stopUsingGps() {
        if (locationM != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationM.removeUpdates(GpsInfo.this);
        }
    }

    // GPS 혹은 인터넷 켜져있는지 확인
    public boolean isGetLocation() {
        return this.isGetlocation;
    }

    // Gps정보를 가져오지 못했을 때 설정값으로 갈지 물어보는 alert창
    public void showSettingAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("GPS 사용유무 셋팅");
        alertDialog.setMessage("GPS 셋팅이 되지 않았을 수도 있습니다. \n 설정창으로 가시겠습니까?");

        // OK������
        alertDialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
    }

   

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



  
}
