package com.example.osmosiss.Models;

import java.util.List;

public class Post {

    private String postId;
    private String postedBy;
    private String courseTitle;
    private String courseSubTitle;
    private String courseDesc;
    private String coursePic;
    private String courseCategory;
    private String courseSubCategory;
    private String courseLanguage;
    private String courseLevel;
    private String coursePrice;
    private List<CourseContent> courseContentList;
    private long postedAt;
    private int postLike;
    private int commentCount;

    public Post(String postId, String postedBy, String courseTitle, String courseSubTitle, String courseDesc,
                String coursePic, String courseCategory, String courseSubCategory, String courseLanguage,
                String courseLevel, String coursePrice, List<CourseContent> courseContentList, long postedAt, int postLike, int commentCount) {
        this.postId = postId;
        this.postedBy = postedBy;
        this.courseTitle = courseTitle;
        this.courseSubTitle = courseSubTitle;
        this.courseDesc = courseDesc;
        this.coursePic = coursePic;
        this.courseCategory = courseCategory;
        this.courseSubCategory = courseSubCategory;
        this.courseLanguage = courseLanguage;
        this.courseLevel = courseLevel;
        this.coursePrice = coursePrice;
        this.courseContentList = courseContentList;
        this.postedAt = postedAt;
        this.postLike = postLike;
        this.commentCount = commentCount;
    }

    public Post(){};

    public List<CourseContent> getCourseContentList() {
        return courseContentList;
    }

    public void setCourseContentList(List<CourseContent> courseContentList) {
        this.courseContentList = courseContentList;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseSubTitle() {
        return courseSubTitle;
    }

    public void setCourseSubTitle(String courseSubTitle) {
        this.courseSubTitle = courseSubTitle;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseSubCategory() {
        return courseSubCategory;
    }

    public void setCourseSubCategory(String courseSubCategory) {
        this.courseSubCategory = courseSubCategory;
    }

    public String getCourseLanguage() {
        return courseLanguage;
    }

    public void setCourseLanguage(String courseLanguage) {
        this.courseLanguage = courseLanguage;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPostLike() {
        return postLike;
    }

    public void setPostLike(int postLike) {
        this.postLike = postLike;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    public long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(long postedAt) {
        this.postedAt = postedAt;
    }
}
