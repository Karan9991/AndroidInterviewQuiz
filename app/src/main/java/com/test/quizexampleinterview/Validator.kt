package com.test.quizexampleinterview

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

class Validator : TextWatcher {
    var isValid = false
        private set

    override fun afterTextChanged(editableText: Editable) {
        isValid = isValidEmail(editableText)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { /*No-op*/
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { /*No-op*/
    }

    companion object {
        val EMAIL_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        )
        val PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +  //at least 1 digit
                "(?=.*[a-z])" +  //at least 1 lower case letter
                "(?=.*[A-Z])" +  //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{6,}" +  //at least 4 characters
                "$")

        @JvmStatic
        fun isValidEmail(email: CharSequence?): Boolean {
            return email != null && EMAIL_PATTERN.matcher(email).matches()
        }

        @JvmStatic
        fun isValidPassword(password: CharSequence?): Boolean {
            return password != null && PASSWORD_PATTERN.matcher(password).matches()
        }

        @JvmStatic
        fun isEmpty(field: String): Boolean {
            return if (field.isEmpty()) {
                true
            } else if (field.trim { it <= ' ' }.length == 0) {
                true
            } else {
                false
            }
        }
    }
}