package com.example.calopsia.tracker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import android.os.Vibrator;

public class MainActivity extends Activity{

    public String server_host = "";
    public String server_params = "";
    public double latitud;
    public double longitud;
    public float speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        LocationDetector myloc = new LocationDetector(this);
        double myLat = 0;
        double myLong = 0;
        float mySpeed = 0;
        if (myloc.canGetLocation)
        {
            myloc.isNetworkEnabled = false;
            Log.v("APK","can get location true - " + myloc.GpsProvider);
            Toast.makeText(this, "Hay datos de localizacion a traves de: " + myloc.GpsProvider , Toast.LENGTH_LONG).show();
            myLat = myloc.getLatitude();
            myLong = myloc.getLongitude();
            mySpeed = myloc.getSpeed();

            Log.v("get location values", Double.toString(myLat)
                + "     " + Double.toString(myLong)
                + "     " + Float.toString(mySpeed));

            Toast.makeText(this, "Latitud: " + myLat
                            + "Longitud: " + myLong
                            + "Velocidad: " + mySpeed,
                    Toast.LENGTH_LONG).show();

            this.latitud = myLat;
            this.longitud = myLong;
            this.speed = mySpeed;

            Data dt = new Data();
            dt.setLatLon(myLat,myLong);
            dt.setSpeed(mySpeed);
        }
        else {
            Log.v("APK","can get location false");
            Toast.makeText(this, "No se puede obtener la localizacion." , Toast.LENGTH_LONG).show();
            //myloc.showSettingsAlert();
        }

    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub
            int level = intent.getIntExtra("level", 0);

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            GlobalValues gv = new GlobalValues();
            gv.plug = isCharging;
            gv.level = level;
            Log.v("APK", String.valueOf(level) + "% " + ((usbCharge) ? "Cargando..." : "Sin cargarse" ));
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}