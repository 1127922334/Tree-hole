package com.luntan.demo.exception;

public enum enum_ErrorCode implements ErrorCode {
    QUESTION_NOT_FOUND("您访问的文章可能被删除了");
    private  String message;

    enum_ErrorCode(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
