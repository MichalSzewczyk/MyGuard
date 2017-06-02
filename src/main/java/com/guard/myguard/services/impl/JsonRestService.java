package com.guard.myguard.services.impl;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guard.myguard.services.interfaces.RestService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonRestService<T> implements RestService<T> {
    private final String urlAddress;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> serialized;

    public JsonRestService(String urlAddress, Class<T> serialized) {
        this.urlAddress = urlAddress;
        this.serialized = serialized;
    }

    @Override
    public String getJsonForRequest(String request) {
        try {
            String fullUrl = urlAddress + request;
            Log.i("Result url", fullUrl);
            URL url = new URL(fullUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                Log.e("Failed", "HTTP error code: "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder sb = new StringBuilder();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            Log.i("Received data", sb.toString());
            conn.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public T parseJsonData(String data) {
        try {
            System.out.println(String.format("Deserializing data: %s", data));
            return objectMapper.readValue(data, serialized);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
