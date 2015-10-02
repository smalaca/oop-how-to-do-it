package com.smalaca.oophowtodoit.domain;

public class CurrencyAlreadySupportedException extends Exception {
    public CurrencyAlreadySupportedException(String message) {
        super(message);
    }
}
