package com.upclicks.laDiva.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("UsernameOrEmailAddress")
    @Expose
    private String usernameOrEmailAddress;
    @SerializedName("Password")
    @Expose
    private String password;

    public LoginRequest(String usernameOrEmailAddress, String password) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
        this.password = password;
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
