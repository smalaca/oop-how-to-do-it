package com.smalaca.oophowtodoit.domain;

import java.util.Currency;

class BankAccount {
    private AccountNumber number;
    private Currency currency;

    public BankAccount(AccountNumber number, Currency currency) {
        this.number = number;
        this.currency = currency;
    }

    public AccountNumber getAccountNumber() {
        return number;
    }

    public boolean isSupportCurrency(Currency currency) {
        return this.currency.equals(currency);
    }

    public boolean supportTheSameCurrencyAs(BankAccount bankAccount) {
        return isSupportCurrency(bankAccount.currency);
    }
}
