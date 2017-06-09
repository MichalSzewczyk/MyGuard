package com.guard.myguard.services.impl;

import android.util.Base64;

import com.guard.myguard.services.interfaces.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public String decrypt(String encoded) {
        byte[] decoded = Base64.decode(encoded, 0);
        return new String(decoded);
    }

    @Override
    public String encode(String pass) {
        byte[] encoded = Base64.encode(pass.getBytes(), 0);
        return new String(encoded);
    }
}
