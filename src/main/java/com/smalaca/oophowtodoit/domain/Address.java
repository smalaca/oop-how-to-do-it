package com.smalaca.oophowtodoit.domain;

public class Address {
    private StreetName street;
    private NonNegative houseNumber;
    private ZipCode zipCode;
    private City city;

    public Address(StreetName street, NonNegative houseNumber, ZipCode zipCode, City city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }
}
