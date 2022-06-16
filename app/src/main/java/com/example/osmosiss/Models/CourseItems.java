package com.example.osmosiss.Models;

public class CourseItems {

    private String courseName;
    private String coursePic;

    public CourseItems(String courseName, String coursePic) {
        this.courseName = courseName;
        this.coursePic = coursePic;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
}
