package com.example.calopsia.tracker;

/**
 * Created by SEbas on 14/02/2017.
 */


public  class Data {
    private int id;
    private int vid;
    private double lat;
    private double lon;
    private float speed;

    public Data()
    {}

    public Data(int id, int vid, double lat,  double lon, float speed)
    {
        this.id = id;
        this.vid = vid;
        this.lat = lat;
        this.lon = lon;
        this.speed = speed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setvId(int vid) {
        this.vid = vid;
    }

    public void setLatLon(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public int getvId() {
        return vid;
    }

    public float getSpeed() {
        return speed;
    }

    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }


}





