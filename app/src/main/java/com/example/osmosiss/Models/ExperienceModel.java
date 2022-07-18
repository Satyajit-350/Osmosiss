package com.example.osmosiss.Models;

public class ExperienceModel {

    private String title,companyName,startDate,endDate,details;

    public ExperienceModel(String title, String companyName, String startDate, String endDate, String details) {
        this.title = title;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.details = details;
    }

    public ExperienceModel(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
