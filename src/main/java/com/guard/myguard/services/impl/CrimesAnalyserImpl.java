package com.guard.myguard.services.impl;

import com.guard.myguard.DangerLevel;
import com.guard.myguard.model.Crime;
import com.guard.myguard.services.interfaces.CrimesAnalyser;

public class CrimesAnalyserImpl implements CrimesAnalyser {
    private Crime[] crimes;
    private final int interval;
    private static final String COLOR_PATTERN = "#80%s%s00";
    private static final int MAX_COLOR_VALUE = 255;

    public CrimesAnalyserImpl(int maxScaleValue) {
        this.interval = maxScaleValue/DangerLevel.values().length;
    }

    public void setCrimes(Crime[] crimes) {
        this.crimes = crimes;
    }

    @Override
    public DangerLevel getDangerLevel() {
        return DangerLevel.getEnumByIntValue(countScaledValue(crimes.length, interval));
    }

    @Override
    public String getColor(int value) {
        return String.format(COLOR_PATTERN, countScaledValue(value, MAX_COLOR_VALUE), MAX_COLOR_VALUE - countScaledValue(value, MAX_COLOR_VALUE));
    }

    private int countScaledValue(int value, int interval) {
        int scaled = 0;
        while (scaled * interval >= value)
            scaled++;
        return --scaled;
    }
}
