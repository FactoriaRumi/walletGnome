package com.rumidev.walletgnome.model;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Kamila on 2015-11-04.
 */
public class Category implements Serializable{

    private long id = -1;
    private String name;
    private int color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int colour) {
        this.color = colour;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                ", color=" + color +'\'' +
                '}';
    }
}
