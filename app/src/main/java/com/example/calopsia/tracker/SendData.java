package com.example.calopsia.tracker;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SEbas on 13/02/2017.
 */

public class SendData {

    public String server_url = null;
    public String params = null;

    public int sendPostData(){

        Log.d("APK","SendPostData");
        String _url = server_url;
        HttpURLConnection httpClient = null;

        String Urlparams = "var_1=1&var_2=2";

        try {
            URL url = new URL(_url);
            httpClient = (HttpURLConnection) url.openConnection();
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpClient.setRequestProperty("Content-Language", "en-US");
            httpClient.setUseCaches(false);
            httpClient.setDoInput(true);
            httpClient.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream());
            wr.writeBytes(Urlparams);
            wr.flush();
            wr.close();
            InputStream is = httpClient.getInputStream();
            int responseCode = httpClient.getResponseCode();
            Log.d("APK"," Codigo de respuesta: " + responseCode);
            return responseCode;

        }
        catch (Exception e) {
            Log.e("TAG", "Exception " + e.getMessage() , e.fillInStackTrace());
            e.printStackTrace();
            return -1;
        }
        finally {
            if (httpClient != null) {
                httpClient.disconnect();
            }
            return 0;
        }

    }
}
