package com.example.osmosiss.Models;

public class Category {

    private String categoryId;
    private String categoryName;
    private String totalCourses;
    private String image;

    public Category(String categoryId, String categoryName, String totalCourses, String image) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalCourses = totalCourses;
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(String totalCourses) {
        this.totalCourses = totalCourses;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
