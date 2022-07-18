package com.example.osmosiss.Models;

public class EducationModel {

    private String instituteName;
    private String degree;
    private String startDate,endDate;
    private String grade;

    public EducationModel(String instituteName, String degree, String startDate, String endDate, String grade) {
        this.instituteName = instituteName;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }

    public EducationModel(){};

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
