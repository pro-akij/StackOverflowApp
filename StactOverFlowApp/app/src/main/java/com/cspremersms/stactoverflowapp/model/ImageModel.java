package com.cspremersms.stactoverflowapp.model;

import com.google.gson.annotations.SerializedName;

public class ImageModel {

    @SerializedName("profile_image")
    private String profile_image;

    public ImageModel(String profile_image) {
        this.setProfile_image(profile_image);
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
