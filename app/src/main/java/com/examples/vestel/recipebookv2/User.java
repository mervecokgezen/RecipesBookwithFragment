package com.examples.vestel.recipebookv2;

/**
 * Created by vestel on 27.03.2018.
 */

public class User {
    private String user_mail;
    private String user_password;

    public User(String user_mail, String user_password) {
        this.user_mail = user_mail;
        this.user_password = user_password;
    }

    public String getUser_mail() {

        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
