package com.example.osmosiss.Models;

public class ProjectModel {

    private String projectTitle,githubLink,startDate,endDate,projectDetail;

    public ProjectModel(String projectTitle, String githubLink, String startDate, String endDate, String projectDetail) {
        this.projectTitle = projectTitle;
        this.githubLink = githubLink;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetail = projectDetail;
    }

    public ProjectModel(){};

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
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

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }
}
