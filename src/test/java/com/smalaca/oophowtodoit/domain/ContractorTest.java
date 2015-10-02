package com.smalaca.oophowtodoit.domain;

import org.junit.Test;

import java.util.Currency;
import java.util.List;

import static com.smalaca.oophowtodoit.helpers.TestDummies.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class ContractorTest {
    private static final Email FAKE_EMAIL_1 = new Email("fake1@email.com");
    private static final Email FAKE_EMAIL_2 = new Email("fake2@email.com");
    private static final Email FAKE_EMAIL_3 = new Email("fake3@email.com");

    private static final AccountNumber FAKE_NUMBER_1 = new AccountNumber("fakeNumber0001");
    private static final AccountNumber FAKE_NUMBER_2 = new AccountNumber("fakeNumber0002");
    private static final AccountNumber FAKE_NUMBER_3 = new AccountNumber("fakeNumber0003");

    private static final Currency CURRENCY_1 = Currency.getInstance("USD");
    private static final Currency CURRENCY_2 = Currency.getInstance("PLN");
    private static final Currency CURRENCY_3 = Currency.getInstance("EUR");

    private final Contractor contractor = new Contractor(DUMMY_EIN, DUMMY_NAME, DUMMY_ADDRESS);

    @Test
    public void shouldReturnOneEmailWhenThereIsOnlyOneContactPerson() {
        contractor.addContactPerson(aContactPerson("Sebastian", FAKE_EMAIL_1));

        List<Email> emails = contractor.getEmails();

        assertThat(emails).containsExactly(FAKE_EMAIL_1);
    }

    @Test
    public void shouldReturnEmailsOfAllContactPersons() {
        contractor.addContactPerson(aContactPerson("Sebastian", FAKE_EMAIL_1));
        contractor.addContactPerson(aContactPerson("Sebastian", FAKE_EMAIL_2));
        contractor.addContactPerson(aContactPerson("Sebastian", FAKE_EMAIL_3));

        List<Email> emails = contractor.getEmails();

        assertThat(emails).containsExactly(FAKE_EMAIL_1, FAKE_EMAIL_2, FAKE_EMAIL_3);
    }

    private ContactPerson aContactPerson(String name, Email email) {
        return new ContactPerson(new Name(name), email);
    }

    @Test
    public void shouldChangeAnAddress() {
        Address newAddress = anAddress();

        contractor.changeAddress(newAddress);

        assertThat(contractor.getDataToInvoice().address).isSameAs(newAddress);
    }

    private Address anAddress() {
        return new Address(new StreetName("New Street"), DUMMY_NUMBER, DUMMY_ZIPCODE, DUMMY_CITY);
    }

    @Test
    public void shouldAddBankAccountForUnsupportedCurrency() throws CurrencyAlreadySupportedException {
        assertThat(contractor.hasBankAccountThatSupportsCurrency(CURRENCY_1)).isFalse();

        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_1, CURRENCY_1));

        assertThat(contractor.hasBankAccountThatSupportsCurrency(CURRENCY_1)).isTrue();
    }

    @Test
    public void shouldRaiseAnExceptionWhenAddingBankAccountForSupportedCurrency() throws CurrencyAlreadySupportedException {
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_1, CURRENCY_1));

        try {
            contractor.addBankAccount(aBankAccount(FAKE_NUMBER_2, CURRENCY_1));

            fail("Contractor already have a bank account that supports particular currency.");
        } catch (CurrencyAlreadySupportedException exception) {
            assertThat(exception.getMessage()).isEqualTo("There is a bank account that supports the same currency.");
        }
    }

    @Test
    public void shouldReturnBankAccountForGivenCurrency() throws UnsupportedCurrencyException, CurrencyAlreadySupportedException {
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_1, CURRENCY_1));
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_2, CURRENCY_2));
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_3, CURRENCY_3));

        AccountNumber accountNumber = contractor.getBankAccountNumberForCurrency(CURRENCY_2);

        assertThat(accountNumber).isSameAs(FAKE_NUMBER_2);
    }

    @Test
    public void shouldThrowExceptionWhenNoBankAccountForGivenCurrencyFound() {
        try {
            contractor.getBankAccountNumberForCurrency(CURRENCY_2);

            fail("Contractor haven't got bank account that supports given currency.");
        } catch (UnsupportedCurrencyException exception) {
            assertThat(exception.getMessage()).isEqualTo("There is no bank account number for given currency: PLN");
        }
    }

    private BankAccount aBankAccount(AccountNumber number, Currency currency) {
        return new BankAccount(number, currency);
    }

    @Test
    public void shouldRemoveBankAccountForCurrency() throws CurrencyAlreadySupportedException {
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_1, CURRENCY_1));

        contractor.removeBankAccountForCurrency(CURRENCY_1);

        assertThat(contractor.hasBankAccountThatSupportsCurrency(CURRENCY_1)).isFalse();
    }

    @Test
    public void shouldNotRemoveBankAccountIfUnsupportedCurrencyGiven() throws CurrencyAlreadySupportedException {
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_1, CURRENCY_1));
        contractor.addBankAccount(aBankAccount(FAKE_NUMBER_2, CURRENCY_2));

        contractor.removeBankAccountForCurrency(CURRENCY_3);

        assertThat(contractor.hasBankAccountThatSupportsCurrency(CURRENCY_1)).isTrue();
        assertThat(contractor.hasBankAccountThatSupportsCurrency(CURRENCY_2)).isTrue();
    }
}