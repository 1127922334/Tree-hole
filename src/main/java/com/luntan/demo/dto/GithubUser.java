package com.luntan.demo.dto;

public class GithubUser {
    public String name;
    private Long id;//账号唯一标示
    private  String bio;//描述
    private  String avatar_url;//头像链接
    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }




}
