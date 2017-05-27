package com.guard.myguard.services.impl;

import com.guard.myguard.model.rest.Crime;
import com.guard.myguard.services.interfaces.CrimesRestApiClient;
import com.guard.myguard.services.interfaces.RestService;

public class CrimesRestApiClientImpl implements CrimesRestApiClient {
    private final RestService<Crime[]> restService;
    private static final String URL = "https://data.police.uk/api/";
    private static final String STREET_LEVEL_CRIMES_AT_LOCATION = "stops-street?lat=%s&lng=%s&date=%s";

    public CrimesRestApiClientImpl() {
        this.restService = new JsonRestService<>(URL, Crime[].class);
    }

    @Override
    public Crime[] getCrimesForLocation(double lat, double lng, String yearAndMonth) {
        return restService.parseJsonData(restService.getJsonForRequest(
                String.format(STREET_LEVEL_CRIMES_AT_LOCATION, String.valueOf(lat), String.valueOf(lng), yearAndMonth)));
    }
}
