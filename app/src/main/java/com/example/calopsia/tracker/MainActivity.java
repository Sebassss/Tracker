package com.example.calopsia.tracker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final static int INTERVAL = 1000 * 60 * 1; //2 minutes
    Button btnShowLocation;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    public double latitud;
    public double longitud;
    public float speed;
    // GPSTracker class
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         /* This code together with the one in onDestroy()
         * will make the screen be always on until this Activity gets destroyed. */
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                //execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnShowLocation = (Button) findViewById(R.id.button);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    latitud = gps.getLatitude();
                    longitud = gps.getLongitude();
                    speed = (gps.getSpeed()*3600)/1000;

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                            + latitud + "\nLong: " + longitud + "\nVelocidad: " + (int)speed + " KM/h", Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }


            }
        });
        startRepeatingTask();
    }


    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable()
    {
        @Override
        public void run() {
            btnShowLocation.performClick();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
            LongOperation lg = new LongOperation();
            lg.execute();
        }
    };

    void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    void stopRepeatingTask()
    {
        mHandler.removeCallbacks(mHandlerTask);
    }
}

class LongOperation extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        //txt.setText("Executed");
        SendData sd = new SendData();
        sd.params = "";
        sd.server_url="http://200.0.236.210:84/movilidadesMSP/public/GpsDataPost/0/0/0.0/2/1/1";
        //sd.sendPostData();
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}