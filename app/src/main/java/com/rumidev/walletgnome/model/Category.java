package com.rumidev.walletgnome.model;

/**
 * Created by Kamila on 2015-11-04.
 */
public class Category {

    private long id = -1;
    private String name;
    private int colour;

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

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                ", colour=" + colour +'\'' +
                '}';
    }
}
