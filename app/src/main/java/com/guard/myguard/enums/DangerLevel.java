package com.guard.myguard.enums;

public enum DangerLevel {
    CHILL, GOOD, MEDIUM, HEIGHT, EXTREME, INCREDIBLE, HELL;

    public static DangerLevel getEnumByIntValue(int value){
        return DangerLevel.values()[value];
    }
}
