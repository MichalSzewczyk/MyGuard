package com.guard.myguard.services.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guard.myguard.R;
import com.guard.myguard.model.db.UserData;
import com.guard.myguard.services.interfaces.EncryptionService;
import com.guard.myguard.services.interfaces.LoginHandler;
import com.guard.myguard.services.interfaces.ParsingService;

public class StoredLoginHandler implements LoginHandler {
    private final static String CRED_KEY = "cred";
    private final ParsingService parsingService;
    private final SharedPreferences settings;
    private final EncryptionService encryptionService;
    private final Context context;

    public StoredLoginHandler(Context context) {
        this.parsingService = new JsonParsingFacade(new ObjectMapper());
        this.settings = PreferenceManager.getDefaultSharedPreferences(context);
        this.encryptionService = new EncryptionServiceImpl();
        this.context = context;
    }

    @Override
    public synchronized UserData getStoredCredentials() {
        String serializedTuple = settings.getString(context.getString(R.string.cred), "null");
        Log.i("deserializing", serializedTuple);
        UserData deserialized = parsingService.deserialize(serializedTuple, UserData.class);
        if(deserialized == null){
            return null;
        }
        String encrypted = deserialized.getPassword();
        String decrypted = encryptionService.decrypt(encrypted);
        deserialized.setPassword(decrypted);
        return deserialized;
    }

    @Override
    public synchronized boolean isDataAvailable() {
        return settings.contains(CRED_KEY);
    }

    @Override
    public synchronized void storeCredentials(String nick, String icePhone, String userPhone, String password) {
        SharedPreferences.Editor editor = settings.edit();
        String encodedPassword = encryptionService.encode(password);
        UserData tuple = new UserData(nick, icePhone, userPhone, encodedPassword);
        String serializedTuple = null;
        try {
            serializedTuple = parsingService.serialize(tuple);
        } catch (Throwable throwable) {
            Log.e("Serialization failed", throwable.toString());
        }
        editor.putString(CRED_KEY, serializedTuple);
        editor.commit();
    }
}
