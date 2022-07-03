package com.example.osmosiss.Models;

public class CourseContent {

    private String title;
    private String videoUri;
    private String pdfUri;

    public CourseContent(String title, String videoUri, String pdfUri) {
        this.title = title;
        this.videoUri = videoUri;
        this.pdfUri = pdfUri;
    }

    public CourseContent(String title, String videoUri) {
        this.title = title;
        this.videoUri = videoUri;
    }

    public CourseContent(String title) {
        this.title = title;
    }
    //    public CourseContent(String title,String pdfUri) {
//        this.title = title;
//        this.pdfUri = pdfUri;
//    }

    public CourseContent(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }
}
