package com.tutorialsaddas.csv;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.tutorialsaddas.csv.database.DatabaseHelper;

public class DatabaseSingleton {

    private static DatabaseSingleton databaseSingleton;

    private DatabaseSingleton() {
    }


    public static DatabaseSingleton getInstance() {

        if (databaseSingleton == null) {

            databaseSingleton = new DatabaseSingleton();
        }
        return databaseSingleton;
    }

    public DatabaseHelper getDatabaseInstance(Context context) {
        return new DatabaseHelper(context.getApplicationContext());
    }
}
