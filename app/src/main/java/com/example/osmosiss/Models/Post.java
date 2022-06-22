package com.example.osmosiss.Models;

public class Post {

    private String postId;
    private String postedBy;
    private String courseTitle;
    private String courseDesc;
    private String coursePic;
    private long postedAt;
    private int postLike;
    private int commentCount;

    public Post(String postId, String postedBy, String courseTitle, String courseDesc, String coursePic, long postedAt) {
        this.postId = postId;
        this.postedBy = postedBy;
        this.courseTitle = courseTitle;
        this.courseDesc = courseDesc;
        this.coursePic = coursePic;
        this.postedAt = postedAt;
    }
    public Post(){};

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
