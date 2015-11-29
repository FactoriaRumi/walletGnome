package com.rumidev.walletgnome.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rumidev.walletgnome.R;
import com.rumidev.walletgnome.database.CategoriesDataSource;
import com.rumidev.walletgnome.model.Category;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ActivityCategoryEdit extends AppCompatActivity implements View.OnClickListener {

    private EditText etCategoryName;
    private EditText etCategoryBudget;
    private Button bSave;
    private Button bDelete;
    private Button bColor;

    private Category category;
    private int initialColor = Color.WHITE;
    private boolean newCategory;

    private AmbilWarnaDialog dialogColor;
    private CategoriesDataSource categoriesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_edit);

        categoriesDataSource = new CategoriesDataSource(this);

        etCategoryName = (EditText) findViewById(R.id.etCategoryName);
        etCategoryBudget = (EditText) findViewById(R.id.etCategoryBudget);

        bSave = (Button) findViewById(R.id.bCategorySave);
        bSave.setOnClickListener(this);
        bDelete = (Button) findViewById(R.id.bCategoryDelete);
        bDelete.setOnClickListener(this);
        bColor = (Button) findViewById(R.id.bCategoryColor);
        bColor.setOnClickListener(this);

        Intent startIntent = getIntent();
        newCategory = startIntent.getBooleanExtra(ActivityCategory.CATEGORY_NEW, true);
        if(newCategory) {
            category = new Category();
        } else {
            category = (Category) startIntent.getSerializableExtra(ActivityCategory.CATEGORY);
            etCategoryName.setText(category.getName());
            initialColor = category.getColor();
        }

        setColorDialog();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id) {
        case R.id.bCategorySave:
            category.setName(etCategoryName.getText().toString());

            if(newCategory) {
                categoriesDataSource.addCategory(category);
            } else {
                categoriesDataSource.updateCategory(category);
            }
            NavUtils.navigateUpFromSameTask(this);
            break;
        case R.id.bCategoryDelete:
            if(category.getId() != -1) {
                categoriesDataSource.deleteCategory(category);
            }
            NavUtils.navigateUpFromSameTask(this);
            break;
        case R.id.bCategoryColor: {
                dialogColor.show();
            break;
        }
        }
    }

    private void setColorDialog() {
        dialogColor = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                category.setColor(color);
            }
        });
    }

    private boolean categoryChanged(String name, int color) {
        if(!category.getName().equals(name) || category.getColor() != color) {
            return true;
        } else {
            return false;
        }
    }
}
