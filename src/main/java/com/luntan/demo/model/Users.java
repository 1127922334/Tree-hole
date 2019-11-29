package com.luntan.demo.model;

public class Users {
    private  Integer id;
    private String name;
    private String Account_Id;
    private  String  token;
    private String AvatarUrl;
    private Long GmtCreate;
    private Long GmtModified;
    private  String bio ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_Id() {
        return Account_Id;
    }

    public void setAccount_Id(String Account_Id) {
        this.Account_Id = Account_Id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String AvatarUrl) {
        this.AvatarUrl = AvatarUrl;
    }

    public Long getGmtCreate() {
        return GmtCreate;
    }

    public void setGmtCreate(Long GmtCreate) {
        this.GmtCreate = GmtCreate;
    }

    public Long getGmtModified() {
        return GmtModified;
    }

    public void setGmtModified(Long GmtModified) {
        this.GmtModified = GmtModified;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
