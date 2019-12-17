package com.luntan.demo.exception;

public enum enum_ErrorCode implements ErrorCode {
    QUESTION_NOT_FOUND(2001,"您访问的文章可能被删除了"),
    COMMENT_NOT_FOUND(2002,"未选择任何问题或回复进行评论"),
    NO_LOGIN(2003,"当前操作需要登录,请登录"),
    SYS_ERROR(2004,"服务冒烟了要不你稍后再试试"),
    TYPE_PARAM_ERROR(2005,"评论类型错误或不存在"),
    COMMENTED_NOT_FOUND(2006,"您回复的评论可能被删除了"),
    CONTENT_IS_EMPTY(2007,"回复不能为空");
    private  Integer code;
    private  String message;

    enum_ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
