package com.example.log3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME="Contact.db";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME="my_contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_D0B = "DoB";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_IMAGE = "image";

    public MyDatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_D0B + " TEXT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_IMAGE + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        Log.v(this.getClass().getName(), TABLE_NAME +
                "database upgrade to version" + newVersion + " - old data lost"
        );
        onCreate(db);
    }

    void addContact(String name, String DoB, String email, int image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_D0B, DoB);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_IMAGE, image);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1 ){
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readDatabase() {
        Cursor cursor=null;
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        if ( db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
