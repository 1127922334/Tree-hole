package com.luntan.demo.model;

import lombok.Data;

@Data
public class Question {
    private  Integer id;
    private String title;
    private  String description;
    private String tag;
    private Long gmtcreate;
    private Long gmtmodified;
    private  Integer creator;
    private  Integer viewCount;
    private  Integer likeCount;
    private  Integer commentCount;


}
