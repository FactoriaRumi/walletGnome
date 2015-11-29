package com.rumidev.walletgnome.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kamila on 2015-11-03.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    public static final String DATABASE_NAME = "wallet.db";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        Log.d("DATABASE_HELPER", instance.toString());

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CategoriesDataSource.createCategoriesTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //TODO change when DB will be solid
        sqLiteDatabase.execSQL(CategoriesDataSource.dropCategories());
        sqLiteDatabase.execSQL(CategoriesDataSource.createCategoriesTableQuery());
    }

    private String createExpensesTable() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE expenses (");
        query.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("amount REAL DEFAULT 0, ");
        query.append("currency TEXT NOT NULL, ");
        query.append("category INTEGER, ");
        query.append("description TEXT)");

        return query.toString();
    }
}
