package com.guard.myguard.tasks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.guard.myguard.model.rest.Crime;
import com.guard.myguard.services.interfaces.CrimesAnalyser;
import com.guard.myguard.services.interfaces.CrimesRestApiClient;

public class CrimesAsyncTask extends AsyncTask<Double, Void, Void> {
    private final RelativeLayout relativeLayout;
    private final GoogleMap mGoogleMap;
    private final CrimesAnalyser crimesAnalyser;
    private final CrimesRestApiClient crimesRestApiClient;
    private final int maxValue;

    public CrimesAsyncTask(RelativeLayout relativeLayout, GoogleMap mGoogleMap, CrimesAnalyser crimesAnalyser, CrimesRestApiClient crimesRestApiClient, int maxValue) {
        this.relativeLayout = relativeLayout;
        this.mGoogleMap = mGoogleMap;
        this.crimesAnalyser = crimesAnalyser;
        this.crimesRestApiClient = crimesRestApiClient;
        this.maxValue = maxValue;
    }

    @Override
    protected Void doInBackground(Double... params) {
        crimesAnalyser.setCrimes(crimesRestApiClient.getCrimesForLocation(params[0], params[1]));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        String color = crimesAnalyser.getColor();
        Log.d("Color", color);
        relativeLayout.setBackgroundColor(Color.parseColor(color));
        for(Crime crime : crimesAnalyser.getCrimes()){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(
                    new LatLng(
                            Double.valueOf(crime.getLocation().getLatitude()),
                            Double.valueOf(crime.getLocation().getLongitude())));
            mGoogleMap.addMarker(markerOptions);
        }
    }
}
