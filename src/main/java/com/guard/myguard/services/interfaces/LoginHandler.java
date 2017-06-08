package com.guard.myguard.services.interfaces;


import com.guard.myguard.model.db.UserData;

public interface LoginHandler {
    void storeCredentials(String nick, String icePhone, String userPhone, String password) ;

    UserData getStoredCredentials();

    boolean isDataAvailable();
}
