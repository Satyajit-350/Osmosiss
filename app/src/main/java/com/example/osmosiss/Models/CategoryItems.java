package com.example.osmosiss.Models;

public class CategoryItems {

    private String categoryName;
    private int categoryPic;

    public CategoryItems(String categoryName, int categoryPic) {
        this.categoryName = categoryName;
        this.categoryPic = categoryPic;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(int categoryPic) {
        this.categoryPic = categoryPic;
    }
}
