package com.luntan.demo.dto;

import com.luntan.demo.model.Users;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String comment;
    private Users user;
    private Integer commentCount;
}
