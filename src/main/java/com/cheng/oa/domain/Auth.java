package com.cheng.oa.domain;

import lombok.Data;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Data
public class Auth extends Authenticator {

    private String username;
    private String password;

    public Auth(String username, String password) {

        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

}
