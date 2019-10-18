package com.cspremersms.stactoverflowapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserReceive {

    @SerializedName("items")
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
