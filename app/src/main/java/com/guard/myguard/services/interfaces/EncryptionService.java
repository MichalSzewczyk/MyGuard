package com.guard.myguard.services.interfaces;

public interface EncryptionService {
    String decrypt(String encrypted);
    String encode(String pass);
}
