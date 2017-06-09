package com.guard.myguard.database;

public class DatabaseData {
    public static final String TABLE_NAME = "personal_data";
    public static final String COLUMN_NICK_TITLE = "nick";
    public static final String COLUMN_PASSWORD_TITLE = "password";
    public static final String COLUMN_ICE_NUMBER_TITLE = "ice_number";
    public static final String COLUMN_PHONE_NUMBER_TITLE = "phone_number";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NICK_TITLE + " TEXT," +
                    COLUMN_PASSWORD_TITLE + " TEXT," +
                    COLUMN_ICE_NUMBER_TITLE + " TEXT," +
                    COLUMN_PHONE_NUMBER_TITLE + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
