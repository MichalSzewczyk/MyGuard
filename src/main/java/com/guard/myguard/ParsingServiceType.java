package com.guard.myguard;


import com.guard.myguard.services.interfaces.CustomParsingService;
import com.guard.myguard.services.interfaces.ParsingService;

public enum ParsingServiceType {
    CUSTOM_SERVICE(CustomParsingService.class);
    private final Class<? extends ParsingService> service;

    ParsingServiceType(Class<? extends ParsingService> service) {
        this.service = service;
    }

    public Class<? extends ParsingService> getService() {
        return service;
    }
}
