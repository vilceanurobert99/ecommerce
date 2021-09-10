package com.ecommerce.utils;

import com.ecommerce.models.UserCreditCard;

public class UserCreditCardBuilder {
    private String cardNumber;
    private String cardHolder;
    private Integer expirationMonth;
    private Integer expirationYear;
    private Integer cvv;

    public static UserCreditCardBuilder builder(){
        return new UserCreditCardBuilder();
    }

    public UserCreditCardBuilder cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public UserCreditCardBuilder cardHolder(String cardHolder){
        this.cardHolder = cardHolder;
        return this;
    }

    public UserCreditCardBuilder expirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public UserCreditCardBuilder expirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }

    public UserCreditCardBuilder cvv(Integer cvv) {
        this.cvv = cvv;
        return this;
    }

    public UserCreditCard build() {
        UserCreditCard creditCard = new UserCreditCard();
        creditCard.setCardHolder(cardHolder);
        creditCard.setCardNumber(cardNumber);
        creditCard.setCvv(cvv);
        creditCard.setExpirationMonth(expirationMonth);
        creditCard.setExpirationYear(expirationYear);
        return creditCard;
    }
}
