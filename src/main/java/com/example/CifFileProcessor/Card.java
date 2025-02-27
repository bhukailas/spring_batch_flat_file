package com.example.CifFileProcessor;

public class Card {
    private String cardNumber;
    private String creditLimit;
    private String expirationDate;
    private String recordType;

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getCreditLimit() {
        return this.creditLimit;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public String getRecordType() {
        return this.recordType;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}