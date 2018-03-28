package com.examples.vestel.recipebookv2;

import android.view.View;

/**
 * Created by vestel on 27.03.2018.
 */

public class Food {
    private String food_name;
    private String food_items;
    private String cooking;
    private String supplementary;
    private String writer;
    private String food_id;

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    private String food_category;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Food(String food_name, String food_items, String cooking, String supplementary, String writer, String food_id, String food_category) {
        this.food_name      = food_name;
        this.food_items     = food_items;
        this.cooking        = cooking;
        this.supplementary  = supplementary;
        this.writer         = writer;
        this.food_id        = food_id;
        this.food_category  = food_category;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_items() {
        return food_items;
    }

    public void setFood_items(String food_items) {
        this.food_items = food_items;
    }

    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }

    public String getSupplementary() {
        return supplementary;
    }

    public void setSupplementary(String supplementary) {
        this.supplementary = supplementary;
    }

    public interface CustomItemClickListener {
        void onItemClick(View v, int position);
    }
}
