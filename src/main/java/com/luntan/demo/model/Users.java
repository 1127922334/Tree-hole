package com.luntan.demo.model;

import lombok.Data;

@Data
public class Users {
    private  Integer id;
    private String name;
    private String Account_Id;
    private  String  token;
    private String AvatarUrl;
    private Long GmtCreate;
    private Long GmtModified;
    private  String bio ;


}
