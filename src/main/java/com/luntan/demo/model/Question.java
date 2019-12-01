package com.luntan.demo.model;

public class Question {
    private  Integer id;
    private String title;
    private  String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private  Integer creator;
    private  Integer viewCount;
    private  Integer likeCount;
    private  Integer commentCount;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciription() {
        return description;
    }

    public void setDesciription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getgmt_create() {
        return gmt_create;
    }

    public void setgmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Long getgmt_modified() {
        return gmt_modified;
    }

    public void setgmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }



}
