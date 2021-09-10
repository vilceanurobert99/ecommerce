package com.ecommerce.models;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "user_credit_cards")
public class UserCreditCard implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CreditCardNumber(message = "Not a valid credit card number")
    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Column(name = "card_holder", unique = true)
    private String cardHolder;

    @NotNull
    @Min(1)
    @Max(12)
    @Column(name = "expiration_month")
    private Integer expirationMonth;

    @NotNull
    @Min(2021)
    @Max(2030)
    @Column(name = "expiration_year")
    private Integer expirationYear;

    @NotNull
    @Min(100)
    @Max(999)
    @Column(name = "cvv")
    private Integer cvv;

    @Column(name = "user_id")
    private Long userId;

    public UserCreditCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCreditCard that = (UserCreditCard) o;

        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (cardHolder != null ? !cardHolder.equals(that.cardHolder) : that.cardHolder != null) return false;
        if (expirationMonth != null ? !expirationMonth.equals(that.expirationMonth) : that.expirationMonth != null)
            return false;
        if (expirationYear != null ? !expirationYear.equals(that.expirationYear) : that.expirationYear != null)
            return false;
        return cvv != null ? cvv.equals(that.cvv) : that.cvv == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "UserCreditCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationMonth=" + expirationMonth +
                ", expirationYear=" + expirationYear +
                ", cvv=" + cvv +
                '}';
    }
}
