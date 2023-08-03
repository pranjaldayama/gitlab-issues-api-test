package com.abnamro.assignment.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Author {

    private String id;
    private String username;
    private String name;
    private String state;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("web_url")
    private String webUrl;
}
