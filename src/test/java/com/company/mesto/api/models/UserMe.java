package com.company.mesto.api.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

public class UserMe {
    private String name;
    private String about;
    private String avatar;

    @JsonProperty("id")
    @JsonAlias("_id")
    private String id;

    private String email;


    public UserMe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
