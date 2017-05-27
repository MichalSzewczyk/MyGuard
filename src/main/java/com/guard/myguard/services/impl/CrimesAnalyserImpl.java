package com.guard.myguard.services.impl;

import android.util.Log;

import com.guard.myguard.DangerLevel;
import com.guard.myguard.model.rest.Crime;
import com.guard.myguard.services.interfaces.CrimesAnalyser;

public class CrimesAnalyserImpl implements CrimesAnalyser {
    private Crime[] crimes;
    private final int interval;
    private static final String COLOR_PATTERN = "#95%s%s00";
    private static final int MAX_COLOR_VALUE = 255;

    public CrimesAnalyserImpl(int maxScaleValue) {
        this.interval = MAX_COLOR_VALUE / maxScaleValue;
        Log.i("interval:", String.valueOf(interval));
    }

    public void setCrimes(Crime[] crimes) {
        this.crimes = crimes;
    }

    @Override
    public Crime[] getCrimes() {
        return crimes;
    }

    @Override
    public DangerLevel getDangerLevel() {
        return DangerLevel.getEnumByIntValue(countScaledValue(crimes.length));
    }

    @Override
    public String getColor() {

        return String.format(COLOR_PATTERN, String.format("%02X", countScaledValue(crimes.length)), String.format("%02X", MAX_COLOR_VALUE - countScaledValue(crimes.length)));
    }

    private int countScaledValue(int value) {
        Log.i("Interval * value = ", interval + " * " + value + " = " + interval * value);
        int result = interval * value;
        return result < 255 ? result : 255;
    }
}
