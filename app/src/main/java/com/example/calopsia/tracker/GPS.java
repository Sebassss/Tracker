package com.example.calopsia.tracker;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by SEbas on 07/02/2017.
 */

public class GPS implements LocationListener
{
    MainActivity mainActivity;
    double lat,lon;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    double getLongitude()
    {
        return lon;
    }

    double getLatitude()
    {
        return  lat;
    }

    @Override
    public void onLocationChanged(Location loc) {
        // Este mŽtodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la detecci—n de un cambio de ubicacion
        loc.getLatitude();
        loc.getLongitude();
        //String Text = "Mi ubicaci—n actual es: " + "\n Lat = "
        //+ loc.getLatitude() + "\n Long = " + loc.getLongitude();
        //messageTextView.setText(Text);
        //this.mainActivity.setLocation(loc);

        Log.d("APK","GPS Latitud: " + loc.getLatitude());
        Log.d("APK","GPS Longitud: " + loc.getLongitude());
        lat = loc.getLatitude();
        lon = loc.getLongitude();


        //TextView txt =  (TextView) mainActivity.findViewById(R.id.txt_Data);
        //txt.setText("Latitud: " + loc.getLatitude() + " " + "Longitud: " + loc.getLongitude() + "Velocidad: " + loc.getSpeed() + " KM/h");
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es desactivado
        //messageTextView.setText("GPS Desactivado");
        mainActivity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        Log.d("APK","GPS Desactivado");
        //TextView txt =  (TextView) mainActivity.findViewById(R.id.txt_Data);
        //txt.setText("GPS Descactivado");
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es activado
        //messageTextView.setText("GPS Activado");
        Log.d("APK","GPS Activado");
        //TextView txt =  (TextView) mainActivity.findViewById(R.id.txt_Data);
        //txt.setText("GPS Activado");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Este mŽtodo se ejecuta cada vez que se detecta un cambio en el
        // status del proveedor de localizaci—n (GPS)
        // Los diferentes Status son:
        // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
        // TEMPORARILY_UNAVAILABLE -> Temp˜ralmente no disponible pero se
        // espera que este disponible en breve
        // AVAILABLE -> Disponible
        Log.d("APK","GPS Disponible");
        //TextView txt =  (TextView) mainActivity.findViewById(R.id.txt_Data);
        //txt.setText("GPS Disponible");
    }


}