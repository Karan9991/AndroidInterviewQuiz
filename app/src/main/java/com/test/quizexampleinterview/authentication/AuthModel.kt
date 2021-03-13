package com.test.quizexampleinterview.authentication

class AuthModel {
    var email: String
        private set
    var password: String? = null
        private set

    constructor(email: String) {
        this.email = email
    }

    constructor(email: String, password: String?) {
        this.email = email
        this.password = password
    }

}