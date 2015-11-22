package com.rumidev.walletgnome.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rumidev.walletgnome.R;
import com.rumidev.walletgnome.database.CategoriesDataSource;
import com.rumidev.walletgnome.database.DatabaseHelper;
import com.rumidev.walletgnome.model.Category;

public class ActivityCategory extends Activity {

    private EditText etCategory;
    private Button bAdd;

    private CategoriesDataSource categoriesDAO;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        dbHelper = DatabaseHelper.getInstance(this);
        categoriesDAO = new CategoriesDataSource(this);

        etCategory = (EditText) findViewById(R.id.etCategory);
        bAdd = (Button) findViewById(R.id.bAdd);
    }



    public void onClick(View view) {

        Category category = null;
        if(view.getId() == R.id.bAdd) {
            category = new Category();
            category.setName(etCategory.getText().toString());
            categoriesDAO.addCategory(category);

        }
        for(Category it : categoriesDAO.getAllCategories()) {
            Log.d("CATEGORIES", it.toString());
        }
    }

    @Override
    protected void onPause() {
        categoriesDAO.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        categoriesDAO.openReadable();
        super.onResume();
    }
}
