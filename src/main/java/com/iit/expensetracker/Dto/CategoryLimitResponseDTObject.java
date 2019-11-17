package com.iit.expensetracker.Dto;

public class CategoryLimitResponseDTObject {
    private String userId;
    private String categoryId;
    private String category;
    private String type;
    private double limit;
    private double totalExpenes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getTotalExpenes() {
        return totalExpenes;
    }

    public void setTotalExpenes(double totalExpenes) {
        this.totalExpenes = totalExpenes;
    }

}
