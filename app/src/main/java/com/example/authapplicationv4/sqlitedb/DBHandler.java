package com.example.authapplicationv4.sqlitedb;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.logging.Logger;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "authAppDb";
    public static final int DB_VERSION = 6;
    public static final String TABLE_NAME = "signUp";

    public static final String ID_COL = "id";
    public static final  String FIRST_NAME = "fname";
    public static final String LAST_NAME= "lname";
    public static final String EMAIL= "email";
    public static final String MOBILE= "mobile";
    public static final String DOB= "dob";
    public static final String GENDER= "gender";
    public static final String LANGUAGE= "language";
    public static final String PASSWORD= "password";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = "CREATE TABLE " + TABLE_NAME + " (" + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT)";
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + MOBILE + " TEXT,"
                + GENDER + " TEXT,"
                + LANGUAGE + " TEXT,"
                + PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    public void saveRegistration(Register register){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST_NAME, register.getFname());
        values.put(LAST_NAME, register.getLname());
        values.put(EMAIL, register.getEmail());
        values.put(MOBILE, register.getMobile());
        values.put(GENDER, register.getGender());
        values.put(LANGUAGE, register.getLanguage());
        values.put(PASSWORD, register.getPassword());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    // we have created a new method for reading all the courses.
    public Boolean signInValidate(String username, String password) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor readCursor = db.rawQuery("SELECT email, password FROM signUp WHERE email = '"+username+"' AND password = '"+password+"'", null);
        if (readCursor.getCount() >= 1){
            return true;
        }
        readCursor.close();
        db.close();
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
