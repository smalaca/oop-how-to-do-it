package com.smalaca.oophowtodoit.domain;

import com.smalaca.oophowtodoit.domain.dto.ContractorDTO;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Contractor {
    private EmployerIdentifcationNumber ein;
    private Name name;
    private List<BankAccount> bankAccounts = new ArrayList<>();
    private List<ContactPerson> contactPersons = new ArrayList<>();
    private Address address;

    public Contractor(EmployerIdentifcationNumber ein, Name name, Address address) {
        this.ein = ein;
        this.name = name;
        this.address = address;
    }

    public ContractorDTO getDataToInvoice()
    {
        return new ContractorDTO(ein, name, address);
    }

    public void addContactPerson(ContactPerson contactPerson) {
        contactPersons.add(contactPerson);
    }

    public List<Email> getEmails() {
        return contactPersons.stream()
                .map(ContactPerson::getEmail)
                .collect(Collectors.toList());
    }

    public void changeAddress(Address address)
    {
        this.address = address;
    }

    public boolean hasBankAccountThatSupportsCurrency(Currency currency) {
        return aBankAccountForCurrency(currency).isPresent();
    }

    public AccountNumber getBankAccountNumberForCurrency(Currency currency) throws UnsupportedCurrencyException {
        Optional<BankAccount> bankAccountForCurrency = aBankAccountForCurrency(currency);

        if (bankAccountForCurrency.isPresent()) {
            return bankAccountForCurrency.get().getAccountNumber();
        }

        throw new UnsupportedCurrencyException("There is no bank account number for given currency: " + currency.getCurrencyCode());
    }

    private Optional<BankAccount> aBankAccountForCurrency(Currency currency) {
        return bankAccounts.stream()
                    .filter((bankAccount) -> bankAccount.isSupportCurrency(currency))
                    .findFirst();
    }

    public void addBankAccount(BankAccount bankAccount) throws CurrencyAlreadySupportedException {
        Optional<BankAccount> bankAccountForCurrency = bankAccounts.stream()
                .filter((presentBankAccount) -> presentBankAccount.supportTheSameCurrencyAs(bankAccount))
                .findAny();

        if (bankAccountForCurrency.isPresent()) {
            throw new CurrencyAlreadySupportedException("There is a bank account that supports the same currency.");
        }

        bankAccounts.add(bankAccount);
    }

    public void removeBankAccountForCurrency(Currency currency)
    {
        Optional<BankAccount> bankAccountForCurrency = aBankAccountForCurrency(currency);

        if (bankAccountForCurrency.isPresent()){
            bankAccounts.remove(bankAccountForCurrency.get());
        }
    }
}
