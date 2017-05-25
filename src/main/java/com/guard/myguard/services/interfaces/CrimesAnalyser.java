package com.guard.myguard.services.interfaces;

import com.guard.myguard.DangerLevel;

public interface CrimesAnalyser {
    DangerLevel getDangerLevel();
    String getColor(int value);
}
