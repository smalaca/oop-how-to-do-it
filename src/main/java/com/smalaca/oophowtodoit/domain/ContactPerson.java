package com.smalaca.oophowtodoit.domain;

class ContactPerson {
    private Name name;
    private Email email;

    public ContactPerson(Name name, Email email) {
        this.name = name;
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
