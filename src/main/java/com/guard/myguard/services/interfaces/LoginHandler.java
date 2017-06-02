package com.guard.myguard.services.interfaces;


import com.guard.myguard.utils.Tuple;

public interface LoginHandler {
    void storeCredentials(String login, String password);

    Tuple<String, String> getStoredCredentials();

    boolean isDataAvailable();
}
