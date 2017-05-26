package com.guard.myguard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_db";
    private static final int DB_VERSION = 0;
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


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( " +
                COLUMN_NICK_TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PASSWORD_TITLE + " TEXT NOT NULL, " +
                COLUMN_ICE_NUMBER_TITLE + " TEXT NOT NULL, " +
                COLUMN_PHONE_NUMBER_TITLE + " TEXT NOT NULL, " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public boolean insertUserValue(String nick, String password, String iceNumber, String userNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NICK_TITLE, nick);
        contentValues.put(COLUMN_PASSWORD_TITLE, password);
        contentValues.put(COLUMN_ICE_NUMBER_TITLE, iceNumber);
        contentValues.put(COLUMN_PHONE_NUMBER_TITLE, userNumber);

        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
