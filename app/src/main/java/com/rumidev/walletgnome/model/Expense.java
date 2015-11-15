package com.rumidev.walletgnome.model;

import java.util.Currency;

/**
 * Created by Kamila on 2015-11-04.
 */
public class Expense {

    private float amount;
    private Currency currency;
    private Category category;
    private String description;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
