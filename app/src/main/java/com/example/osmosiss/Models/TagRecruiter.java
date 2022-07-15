package com.example.osmosiss.Models;

public class TagRecruiter {

    private String profile_pic;
    private String name;
    private String profession;

    public TagRecruiter(String profile_pic, String name) {
        this.profile_pic = profile_pic;
        this.name = name;
    }

    public TagRecruiter(String profile_pic, String name, String profession) {
        this.profile_pic = profile_pic;
        this.name = name;
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
