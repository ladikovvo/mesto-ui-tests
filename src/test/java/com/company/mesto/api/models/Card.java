package com.company.mesto.api.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

import java.util.List;

public class Card {
    private List<String> likes;

    @JsonProperty("id")
    @JsonAlias("_id")
    private String id;
    private String name;
    private String link;
    private String owner;
    private String createdAt;

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
