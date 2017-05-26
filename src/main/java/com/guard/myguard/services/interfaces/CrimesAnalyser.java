package com.guard.myguard.services.interfaces;

import com.guard.myguard.DangerLevel;
import com.guard.myguard.model.rest.Crime;

public interface CrimesAnalyser {
    DangerLevel getDangerLevel();
    String getColor();
    void setCrimes(Crime[] crimes);
    Crime[] getCrimes();
}
