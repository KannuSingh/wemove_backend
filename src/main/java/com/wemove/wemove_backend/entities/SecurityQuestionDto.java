package com.wemove.wemove_backend.entities;

public class SecurityQuestionDto {
    private String securityQuestion;
    private String email;

    public SecurityQuestionDto(String securityQuestion, String email) {
        this.securityQuestion = securityQuestion;
        this.email = email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
