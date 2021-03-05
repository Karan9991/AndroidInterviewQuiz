package com.test.quizexampleinterview.authentication;

public class AuthModel {

    private String email;
    private String password;

    public AuthModel(String email) {
        this.email = email;
    }

    public AuthModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
