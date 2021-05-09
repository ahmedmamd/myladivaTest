package com.upclicks.laDiva.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Surname")
    @Expose
    private String surname;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("VerificationCode")
    @Expose
    private  String VerificationCode;

    public SignUpRequest(String password, String name, String surname, String phoneNumber) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVerificationCode(String verificationCode) {
        VerificationCode = verificationCode;
    }
}
