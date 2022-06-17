package com.example.osmosiss.Models;

public class CourseLessonDetail {

    private String name;
    private String videoUrl;

    public CourseLessonDetail(String name, String videoUrl) {
        this.name = name;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
