package com.luntan.demo.enums;

public enum  CommentTypeEnums {
    QUESTION(1),
    COMMENT(2)
    ;
    private  Integer Type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnums commentTypeEnum: CommentTypeEnums.values()){
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return Type;
    }

    CommentTypeEnums(Integer type) {
        Type = type;
    }
}
