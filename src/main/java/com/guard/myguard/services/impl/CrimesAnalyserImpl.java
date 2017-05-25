package com.guard.myguard.services.impl;

import com.guard.myguard.DangerLevel;
import com.guard.myguard.model.Crime;
import com.guard.myguard.services.interfaces.CrimesAnalyser;

public class CrimesAnalyserImpl implements CrimesAnalyser {
    private Crime[] crimes;
    private final int interval;

    public CrimesAnalyserImpl(int maxScaleValue) {
        this.interval = maxScaleValue/DangerLevel.values().length;
    }

    public void setCrimes(Crime[] crimes) {
        this.crimes = crimes;
    }

    @Override
    public DangerLevel getDangerLevel() {
        return DangerLevel.getEnumByIntValue(countScaledValue(crimes.length));
    }

    private int countScaledValue(int value) {
        int scaled = 0;
        while (scaled * interval >= value)
            scaled++;
        return --scaled;
    }
}
