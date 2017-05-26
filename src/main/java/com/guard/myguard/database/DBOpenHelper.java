package com.guard.myguard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_db";
    private static final int DB_VERSION = 0;
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_SURNAME = "SURNAME";
    public static final String KIDS_TABLE_NAME = "CRED";
    public static final String CLOTHES_TABLE_NAME = "CLOTHES";
    public static final String COL_COAT = "COAT";
    public static final String COL_CAP = "CAP";
    public static final String COL_VEST = "VEST";
    public static final String COL_SHOES = "SHOES";
    public static final String COL_POLAR = "POLAR";
    public static final String COL_GLOVES = "GLOVES";
    public static final String COL_SHORTS = "SHORTS";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + KIDS_TABLE_NAME + "( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_SURNAME + " TEXT NOT NULL, " +
                ")");

        db.execSQL("CREATE TABLE " + CLOTHES_TABLE_NAME + "( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_COAT + " BOOLEAN NOT NULL, " +
                COL_CAP + " BOOLEAN NOT NULL, " +
                COL_VEST + " BOOLEAN NOT NULL, " +
                COL_SHOES + " BOOLEAN NOT NULL, " +
                COL_POLAR + " BOOLEAN NOT NULL, " +
                COL_GLOVES + " BOOLEAN NOT NULL, " +
                COL_SHORTS + " BOOLEAN NOT NULL " +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KIDS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CLOTHES_TABLE_NAME);
        this.onCreate(db);
    }

    public boolean insertClothesValue(boolean coat, boolean cap, boolean vest, boolean shoes, boolean polar, boolean gloves, boolean shorts) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_COAT, coat);
        contentValues.put(COL_CAP, cap);
        contentValues.put(COL_VEST, vest);
        contentValues.put(COL_SHOES, shoes);
        contentValues.put(COL_POLAR, polar);
        contentValues.put(COL_GLOVES, gloves);
        contentValues.put(COL_SHORTS, shorts);
        return db.insert(CLOTHES_TABLE_NAME, null, contentValues) != -1;
    }

    public boolean insertUserData(String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SURNAME, surname);
        return db.insert(KIDS_TABLE_NAME, null, contentValues) != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT * FROM " + KIDS_TABLE_NAME + " k INNER JOIN "+CLOTHES_TABLE_NAME + " c ON k._id = c._id", null);
    }

//    public boolean updateValue(int id, String name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_ID, id);
//        contentValues.put(COL_NAME, name);
//        return db.update(TABLE_NAME, contentValues, COL_ID + "=" + id, null) != -1;
//    }

//    public boolean deleteValue(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, COL_ID + "=" + id, null) != -1;
//    }


}
