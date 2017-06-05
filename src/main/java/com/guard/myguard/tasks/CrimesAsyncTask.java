package com.guard.myguard.tasks;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.guard.myguard.R;
import com.guard.myguard.model.rest.Crime;
import com.guard.myguard.services.interfaces.CrimesAnalyser;
import com.guard.myguard.services.interfaces.CrimesRestApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CrimesAsyncTask extends AsyncTask<Double, Void, Crime[]> {
    private final RelativeLayout relativeLayout;
    private final GoogleMap mGoogleMap;
    private final CrimesAnalyser crimesAnalyser;
    private final CrimesRestApiClient crimesRestApiClient;
    private final int maxValue;
    private static final String CRIME_INFO = "Date: %s\nGender: %s\nAge range: %s\nPlace: %s\nLegislation: %s\nCrime:%s";
    private final Activity activity;

    public CrimesAsyncTask(Activity activity, RelativeLayout relativeLayout, GoogleMap mGoogleMap, CrimesAnalyser crimesAnalyser, CrimesRestApiClient crimesRestApiClient, int maxValue) {
        this.relativeLayout = relativeLayout;
        this.mGoogleMap = mGoogleMap;
        this.crimesAnalyser = crimesAnalyser;
        this.crimesRestApiClient = crimesRestApiClient;
        this.maxValue = maxValue;
        this.activity = activity;
    }

    @Override
    protected Crime[] doInBackground(Double... params) {
        List<Crime> crimeList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)-1;
        for (int i = 1; i < 13; i++) {
            if(month == 0){
                year--;
                month+=12;

            }
            String actual = year + "-" + String.format(Locale.ENGLISH, "%02d", (month--));
            crimeList.addAll(Arrays.asList(crimesRestApiClient.getCrimesForLocation(params[0], params[1], actual)));
        }
        Crime[] crimes = new Crime[crimeList.size()];
        for (int i = 0 ; i< crimes.length ; i++) {
            crimes[i] = crimeList.get(i);
        }
        return crimes;
    }

    @Override
    protected void onPostExecute(Crime[] crimes) {
        super.onPostExecute(crimes);
        crimesAnalyser.setCrimes(crimes);
        String color = crimesAnalyser.getColor();
        Log.d("Color", color);
        int c = Color.parseColor(color);
        Log.i("Parsed color", String.valueOf(c));
        relativeLayout.setBackgroundColor(c);
        for (Crime crime : crimesAnalyser.getCrimes()) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(
                    new LatLng(
                            Double.valueOf(crime.getLocation().getLatitude()),
                            Double.valueOf(crime.getLocation().getLongitude())))
                    .title(crime.getObjectOfSearch())
                    .snippet(String.format(CRIME_INFO, crime.getDatetime().substring(0, 10), crime.getGender(), crime.getAgeRange(), crime.getLocation().getStreet(), crime.getLegislation(), crime.getObjectOfSearch()));
            mGoogleMap.addMarker(markerOptions);
            Log.i("Market inserting", "Inserting marker into position: " + crime.getLocation().getLatitude() + ", " + crime.getLocation().getLongitude());
        }
        startNotification();
    }

    private void startNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(activity)
                .setContentTitle("Event tracker")
                .setContentText("Events received");
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Event tracker details:");

            inboxStyle.addLine("You are in danger!");
        mBuilder.setStyle(inboxStyle);

    }
}
