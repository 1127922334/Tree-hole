package com.luntan.demo.dto;

public class GithubUser {
    public String name;
    private Long id;//账号唯一标示
    private  String bio;//描述

    public String getName() {
        return name;
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
