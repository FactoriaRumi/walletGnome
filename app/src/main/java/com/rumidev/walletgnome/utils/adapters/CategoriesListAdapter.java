package com.rumidev.walletgnome.utils.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rumidev.walletgnome.R;
import com.rumidev.walletgnome.activity.ActivityCategory;
import com.rumidev.walletgnome.database.CategoriesDataSource;
import com.rumidev.walletgnome.model.Category;

import java.util.List;

/**
 * Created by Kamila on 2015-11-23.
 */
public class CategoriesListAdapter extends BaseAdapter {

    private List<Category> categories;

    private Context context;

    public CategoriesListAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return this.categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_category, parent, false);
        }

        TextView categoryName = (TextView) convertView.findViewById(R.id.tvCategoryName);

        Category category = categories.get(position);

        categoryName.setText(category.getName());
        categoryName.setBackgroundColor(category.getColor());
        categoryName.setTextColor(determineTextColor(category));

        return convertView;
    }

    private int determineTextColor(Category category) {
        int red = Color.red(category.getColor());
        int green = Color.green(category.getColor());
        int blue = Color.blue(category.getColor());

        if((red*0.2126 + green*0.7152 + blue*0.0722) > 186) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }
}
