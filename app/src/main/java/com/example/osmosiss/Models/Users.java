package com.example.osmosiss.Models;

import java.util.List;

public class Users {

    private String username;
    private String password;
    private String email;
    private int isAdmin;
    private String profile_pic;
    private String about;
    private String phone;
    private String country;
    private String profession;
    private List<String> skills;
    private List<EducationModel> education;
    private List<ExperienceModel> experience;
    private List<ProjectModel> project;

    public Users(String username, String password, String email, int isAdmin, String profile_pic,
                 String about, String phone, String country, String profession, List<String> skills,
                 List<EducationModel> education, List<ExperienceModel> experience, List<ProjectModel> project) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.profile_pic = profile_pic;
        this.about = about;
        this.phone = phone;
        this.country = country;
        this.profession = profession;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.project = project;
    }

    public Users(String username, String password, String email, int isAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public Users(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<EducationModel> getEducation() {
        return education;
    }

    public void setEducation(List<EducationModel> education) {
        this.education = education;
    }

    public List<ExperienceModel> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceModel> experience) {
        this.experience = experience;
    }

    public List<ProjectModel> getProject() {
        return project;
    }

    public void setProject(List<ProjectModel> project) {
        this.project = project;
    }
}
