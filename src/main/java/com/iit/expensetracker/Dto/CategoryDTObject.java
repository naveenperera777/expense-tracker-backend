package com.iit.expensetracker.Dto;

import com.iit.expensetracker.enums.CategoryEnum;

public class CategoryDTObject {
    private String user_id;
    private String category_id;
    private String category_name;
    private CategoryEnum category_type;
    private double category_limit;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public CategoryEnum getCategory_type() {
        return category_type;
    }

    public void setCategory_type(CategoryEnum category_type) {
        this.category_type = category_type;
    }

    public double getCategory_limit() {
        return category_limit;
    }

    public void setCategory_limit(double category_limit) {
        this.category_limit = category_limit;
    }


}
