package com.smalaca.oophowtodoit.helpers;

import com.smalaca.oophowtodoit.domain.*;

public class TestDummies {
    public static final EmployerIdentifcationNumber DUMMY_EIN = new EmployerIdentifcationNumber("number");
    public static final Name DUMMY_NAME = new Name("name");

    public static final StreetName DUMMY_STREET = new StreetName("street");
    public static final NonNegative DUMMY_NUMBER = new NonNegative(13);
    public static final ZipCode DUMMY_ZIPCODE = new ZipCode("13-999");
    public static final City DUMMY_CITY = new City("Kraków");
    public static final Address DUMMY_ADDRESS = new Address(DUMMY_STREET, DUMMY_NUMBER, DUMMY_ZIPCODE, DUMMY_CITY);

    public static final AccountNumber DUMMY_ACCOUNT_NUMBER = new AccountNumber("dummyNumber");
}
