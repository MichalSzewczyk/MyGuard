package com.guard.myguard.services.interfaces;

import java.util.List;

public interface RestService<T> {
    String getJsonForRequest(String request);
    T parseJsonData(String data);
}
