package com.example.android.bookstorep2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.bookstorep2.data.BookContract.BookEntry;

/**
 * Created by roix on 8/24/18.
 */

public class BookDbHelper extends SQLiteOpenHelper {

    //Name of the database
    private static final String DATABASE_NAME = "books.db";

    //Version of the database
    private static final int DATABASE_VERSION = 1;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Creates a new table into the db
        String SQL_BOOK_TABLE = "CREATE TABLE "
                + BookEntry.TABLE_NAME + " ("
                + BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookEntry.BOOK_NAME + " TEXT NOT NULL, "
                + BookEntry.BOOK_PRICE + " INTEGER NOT NULL, "
                + BookEntry.BOOK_QUANTITY + " INTEGER NOT NULL, "
                + BookEntry.BOOK_SUPPLIER_NAME + " TEXT,"
                + BookEntry.BOOK_SUPPLIER_PHONE + " LONG NOT NULL );";

        // Execute the SQL statement
        sqLiteDatabase.execSQL(SQL_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
