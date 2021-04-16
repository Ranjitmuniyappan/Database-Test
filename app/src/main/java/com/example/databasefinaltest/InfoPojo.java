package com.example.databasefinaltest;

public class InfoPojo {

    private String UserId;
    private String Password;

    public InfoPojo(String userId, String password) {
        UserId = userId;
        Password = password;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "InfoPojo{" +
                "UserId='" + UserId + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
