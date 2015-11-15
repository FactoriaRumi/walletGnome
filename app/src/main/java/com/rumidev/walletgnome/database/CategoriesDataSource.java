package com.rumidev.walletgnome.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rumidev.walletgnome.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamila on 2015-11-09.
 */
public class CategoriesDataSource {

    private static final String TABLE_NAME = "categories";
    private static final String KEY_ID = "id";
    private static final String PROPERTIES_ID = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String KEY_NAME = "name";
    private static final String PROPERTIES_NAME = "TEXT NOT NULL";
    private static final String KEY_COLOUR = "colour";
    private static final String PROPERTIES_COLOUR = "INTEGER NOT NULL";

    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_COLOUR};

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public CategoriesDataSource(Context context) {
        this.dbHelper = DatabaseHelper.getInstance(context);
    }

    private void openWritable() {
        this.database = dbHelper.getWritableDatabase();
    }

    public void openReadable() {
        this.database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public static String createCategoriesTableQuery() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE " + TABLE_NAME + " (");
        query.append(KEY_ID + " " + PROPERTIES_ID + ", ");
        query.append(KEY_NAME + " " + PROPERTIES_NAME + ", ");
        query.append(KEY_COLOUR + " " + PROPERTIES_COLOUR + ")");

        return query.toString();
    }

    public static String dropCategories() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public Category addCategory(Category category) {
        Log.d("addCategory", category.toString());

        openWritable();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, category.getName());
        contentValues.put(KEY_COLOUR, category.getColour());

        long insertId = database.insert(TABLE_NAME, null, contentValues);
        category.setId(insertId);

//        close();

        return category;
    }

    public Category getCategory(long id) {
//        openReadable();

        Cursor cursor = database.query(TABLE_NAME, COLUMNS, " id = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Category category = new Category();
        category.setId(Long.parseLong(cursor.getString(0)));
        category.setName(cursor.getString(1));
        category.setColour(cursor.getInt(2));

        Log.d("getCategory(" + id + ")", category.toString());

        cursor.close();
//        close();

        return category;
    }

    public void deleteCategory(Category category) {
        Log.d("deleteCategory", category.toString());

        long id = category.getId();
        if(id > 0) {
            database.delete(TABLE_NAME, KEY_ID + " = " + id, null);
            Log.d("deleteCategory", "DELETE SUCCESSFUL");
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Category category = new Category();
            category.setId(cursor.getLong(0));
            category.setName(cursor.getString(1));
            category.setColour(cursor.getInt(2));
            categories.add(category);
            cursor.moveToNext();
        }

        cursor.close();

        return categories;
    }
}
