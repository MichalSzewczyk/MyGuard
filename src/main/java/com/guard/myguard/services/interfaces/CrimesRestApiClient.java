package com.guard.myguard.services.interfaces;

import com.guard.myguard.model.rest.Crime;

public interface CrimesRestApiClient {
    Crime[] getCrimesForLocation(double lat, double lng);
}
