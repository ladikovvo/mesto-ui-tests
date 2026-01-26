package com.company.mesto.api.models;

public class UpdateProfileRequest {
    private String name;
    private String about;

    public UpdateProfileRequest(String name, String about) {
        this.name = name;
        this.about = about;
    }

    public String getName() { return name; }
    public String getAbout() { return about; }
}
