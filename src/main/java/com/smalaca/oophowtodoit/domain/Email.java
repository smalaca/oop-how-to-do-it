package com.smalaca.oophowtodoit.domain;

public class Email {
    private final String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String emailAddress() {
        return emailAddress;
    }
}
