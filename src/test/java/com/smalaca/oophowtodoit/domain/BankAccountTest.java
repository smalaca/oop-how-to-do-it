package com.smalaca.oophowtodoit.domain;

import org.junit.Test;

import java.util.Currency;

import static com.smalaca.oophowtodoit.helpers.TestDummies.DUMMY_ACCOUNT_NUMBER;
import static org.assertj.core.api.StrictAssertions.assertThat;

public class BankAccountTest {
    private static final Currency SUPPORTED_CURRENCY = Currency.getInstance("EUR");
    private static final Currency NOT_SUPPORTED_CURRENCY = Currency.getInstance("USD");

    private final BankAccount bankAccount = new BankAccount(DUMMY_ACCOUNT_NUMBER, SUPPORTED_CURRENCY);

    @Test
    public void shouldRecognizeSupportedCurrency() {
        assertThat(bankAccount.isSupportCurrency(SUPPORTED_CURRENCY)).isTrue();
    }

    @Test
    public void shouldRecognizeNotSupportedCurrency() {
        assertThat(bankAccount.isSupportCurrency(NOT_SUPPORTED_CURRENCY)).isFalse();
    }

    @Test
    public void shouldRecognizeIfBankcAccountSupportsTheSameCurrency() {
        assertThat(bankAccount.supportTheSameCurrencyAs(aBankAccount(SUPPORTED_CURRENCY))).isTrue();
    }

    @Test
    public void shouldRecognizeWhenBankcAccountDoesNotSupportTheSameCurrency() {
        assertThat(bankAccount.supportTheSameCurrencyAs(aBankAccount(NOT_SUPPORTED_CURRENCY))).isFalse();
    }

    private BankAccount aBankAccount(Currency currency) {
        return new BankAccount(DUMMY_ACCOUNT_NUMBER, currency);
    }
}