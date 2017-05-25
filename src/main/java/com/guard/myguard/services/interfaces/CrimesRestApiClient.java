package com.guard.myguard.services.interfaces;

import com.guard.myguard.model.Crime;

public interface CrimesRestApiClient {
    Crime[] getCrimesForLocation(double lat, double lng);
}
