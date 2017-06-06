package com.guard.myguard.services.impl;

import android.util.Log;

import com.guard.myguard.enums.ParsingServiceType;
import com.guard.myguard.services.interfaces.ParsingService;

import java.util.HashMap;
import java.util.Map;

public class ParsingServiceFactory {
    private final static Map<ParsingServiceType, ParsingService> services = new HashMap<>();

    public static ParsingService getService(ParsingServiceType parsingServiceType) {
        if (services.containsKey(parsingServiceType)) {
            return services.get(parsingServiceType);
        } else {
            ParsingService tmp = null;
            try {
                tmp = parsingServiceType.getService().newInstance();
                services.put(parsingServiceType, tmp);
            } catch (InstantiationException | IllegalAccessException e) {
                Log.e("Failure", "Required service couldn't be instantiated.", e);
            }

            return tmp;
        }
    }
}
