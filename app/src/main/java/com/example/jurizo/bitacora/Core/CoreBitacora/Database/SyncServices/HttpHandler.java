package com.example.jurizo.bitacora.Core.CoreBitacora.Database.SyncServices;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Carlos Rizo on 25/06/2017.
 */

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler(){

    }

    public String makeServicesCall(String reqUrl) throws ProtocolException{
        String response = null;
        try{
            URL url = new URL(reqUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            response = convertStreamToString(inputStream);
        }catch (MalformedURLException ex){
            Log.e(TAG, "MalformedURLException: " + ex.getMessage());
        }catch (IOException ex){
            Log.e(TAG, "IOException: " + ex.getMessage());
        }catch (Exception ex){
            Log.e(TAG, "Exception: " + ex.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try{
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line).append('\n');
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try{
                inputStream.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        return  stringBuilder.toString();
    }
}
