package com.luntan.demo.dto;

import com.luntan.demo.model.Users;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private Users user;

}