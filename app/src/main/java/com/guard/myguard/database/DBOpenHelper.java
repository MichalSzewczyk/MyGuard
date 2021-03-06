package com.guard.myguard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.guard.myguard.model.db.UserData;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_db";
    private static final int DB_VERSION = 2;
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
    private static final String INSERT_QUERY =
            "INSERT INTO personal_data(%s,%s,%s,%s) VALUES (%s,%s,%s,%s)";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( \"" +
                COLUMN_NICK_TITLE + "\" TEXT NOT NULL, \"" +
                COLUMN_PASSWORD_TITLE + "\" TEXT NOT NULL, \"" +
                COLUMN_ICE_NUMBER_TITLE + "\" TEXT NOT NULL, \"" +
                COLUMN_PHONE_NUMBER_TITLE + "\" TEXT NOT NULL" +
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

    public boolean isAlreadyInDatabase(String login, Cursor cursor){
        boolean exists = false;
        while (!cursor.isAfterLast()) {
            String[] cols = cursor.getColumnNames();
            Log.i("login:", cols[0]);
            if(cols[0].equals(login)){
                exists = true;
                break;
            }
            cursor.moveToNext();
        }
        return exists;
    }

    public UserData getFullData(String login, String password, Cursor cursor){
        if (cursor.moveToFirst() ){
            String[] columnNames = cursor.getColumnNames();
            do {
                String nick = cursor.getString(
                        cursor.getColumnIndex(columnNames[0]));
                String pass = cursor.getString(
                        cursor.getColumnIndex(columnNames[1]));
                String iceNumber = cursor.getString(
                        cursor.getColumnIndex(columnNames[2]));
                String myNumber = cursor.getString(
                        cursor.getColumnIndex(columnNames[3]));
                Log.i("nick", nick +" "+login);
                Log.i("pass", pass +" "+password);
                Log.i("ice num", iceNumber);
                Log.i("my num", myNumber);
                if(nick.equals(login) && pass.equals(password)){
                    return new UserData(nick, iceNumber, myNumber, password);
                }
            } while (cursor.moveToNext());
        }
        return null;
    }
}
