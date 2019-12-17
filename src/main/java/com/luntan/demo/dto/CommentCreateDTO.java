package com.luntan.demo.dto;

public class CommentCreateDTO {
    private  Long parentId;
    private  String comment;
    private  Integer type;//判断是不是评论

    public Long getParentId() {
        return parentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
