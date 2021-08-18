package com.example.demo.models;

import com.example.demo.Validation.Priority;
import com.example.demo.Validation.Rating;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginViewModel {

    @NotBlank( message="User name, password and OTP can't be empty")
    private String userName;
    @NotBlank(message = "Password can't be empty")
    private String Password;
    @NotBlank(message = "OTP can't be empty")
    private String Otp;

    public LoginViewModel() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }
}
