package com.guard.myguard.services.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guard.myguard.services.impl.JsonParsingFacade;


public class CustomParsingService extends JsonParsingFacade {
    public CustomParsingService() {
        super(new ObjectMapper());
    }
}
