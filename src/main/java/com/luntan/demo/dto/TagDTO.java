package com.luntan.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String catrgoryName;
    private List<String> tags;
}
