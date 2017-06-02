package com.guard.myguard.services.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guard.myguard.services.interfaces.EncryptionService;
import com.guard.myguard.services.interfaces.LoginHandler;
import com.guard.myguard.services.interfaces.ParsingService;
import com.guard.myguard.utils.Tuple;

public class StoredLoginHandler implements LoginHandler {
    private final static String CRED_KEY = "cred";
    private final ParsingService parsingService;
    private final SharedPreferences settings;
    private final EncryptionService encryptionService;

    public StoredLoginHandler(Context context) {
        parsingService = new JsonParsingFacade(new ObjectMapper());
        this.settings = PreferenceManager.getDefaultSharedPreferences(context);
        this.encryptionService = new EncryptionServiceImpl();
    }

    @Override
    public synchronized Tuple<String, String> getStoredCredentials() {
        String serializedTuple = settings.getString(CRED_KEY, "null");
        Tuple<String, String> deserialized = parsingService.deserialize(serializedTuple, Tuple.class);
        if(deserialized == null){
            throw new NullPointerException("Deserialization failed");
        }
        String encrypted = deserialized.getValue();
        String decrypted = encryptionService.decrypt(encrypted);
        return new Tuple<>(deserialized.getKey(), decrypted);
    }

    @Override
    public synchronized boolean isDataAvailable() {
        return settings.contains(CRED_KEY);
    }

    @Override
    public synchronized void storeCredentials(String login, String password) {
        SharedPreferences.Editor editor = settings.edit();
        String encodedPassword = encryptionService.encode(password);
        Tuple tuple = new Tuple<>(login, encodedPassword);
        String serializedTuple = null;
        try {
            serializedTuple = parsingService.serialize(tuple);
        } catch (Throwable throwable) {
            Log.e("Serialization failed", throwable.toString());
        }
        editor.putString(CRED_KEY, serializedTuple);
        editor.apply();
    }
}
