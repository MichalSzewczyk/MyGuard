package com.guard.myguard.services.interfaces;

import com.guard.myguard.DangerLevel;
import com.guard.myguard.model.Crime;

public interface CrimesAnalyser {
    DangerLevel getDangerLevel();
    String getColor(int value);
    void setCrimes(Crime[] crimes);
}
