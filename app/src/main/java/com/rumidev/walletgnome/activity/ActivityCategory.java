package com.rumidev.walletgnome.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.rumidev.walletgnome.R;
import com.rumidev.walletgnome.database.CategoriesDataSource;
import com.rumidev.walletgnome.database.DatabaseHelper;
import com.rumidev.walletgnome.model.Category;
import com.rumidev.walletgnome.utils.adapters.CategoriesListAdapter;

public class ActivityCategory extends Activity implements AdapterView.OnItemClickListener{

    public static final String CATEGORY_NEW = "CategoryNew";

    public static final String CATEGORY = "Category";

    private ListView lvCategories;
    private FloatingActionButton fabAddCategory;

    private CategoriesDataSource categoriesDAO;
    private CategoriesListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoriesDAO = new CategoriesDataSource(this);

        lvCategories = (ListView) findViewById(R.id.lvCategories);
        fabAddCategory = (FloatingActionButton) findViewById(R.id.bAddCategory);

        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCategoryEdit.class);
                intent.putExtra(CATEGORY_NEW, true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        categoriesDAO.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        categoriesDAO.openReadable();
        listAdapter = new CategoriesListAdapter(this, categoriesDAO.getAllCategories());
        lvCategories.setAdapter(listAdapter);
        lvCategories.setOnItemClickListener(this);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Category category = (Category) listAdapter.getItem(position);
        Intent intent = new Intent(view.getContext(), ActivityCategoryEdit.class);
        intent.putExtra(CATEGORY_NEW, false);
        intent.putExtra(CATEGORY, category);
        startActivity(intent);
    }
}
