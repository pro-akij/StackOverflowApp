package com.cspremersms.stactoverflowapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class User {
    @SerializedName("location")
    private String location;

    @SerializedName("display_name")
    private String userName;

    @SerializedName("reputation")
    private String reputation;

    @SerializedName("website_url")
    private String website;

    @SerializedName("user_id")
    private String userid;

    @SerializedName("profile_image")
    private String profile_image;

    @SerializedName("badge_counts")
    private HashMap<String, Integer> badges= new HashMap<>();

    /*public User(String location, String userName, String reputation, String website,
                String userid, String profile_image, HashMap<String, Integer> badges) {
        this.location = location;
        this.userName = userName;
        this.reputation = reputation;
        this.website = website;
        this.userid = userid;
        this.profile_image = profile_image;
        this.badges = badges;
    }*/

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public HashMap<String, Integer> getBadges() {
        return badges;
    }

    public void setBadges(HashMap<String, Integer> badges) {
        this.badges = badges;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
