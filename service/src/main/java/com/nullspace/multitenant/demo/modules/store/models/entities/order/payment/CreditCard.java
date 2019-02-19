package com.nullspace.multitenant.demo.modules.store.models.entities.order.payment;

import com.nullspace.multitenant.demo.modules.store.models.entities.payment.CreditCardType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CreditCard {
    @Column(name ="card_type")
    @Enumerated(value = EnumType.STRING)
    private CreditCardType cardType;

    @Column (name ="cc_owner")
    private String ccOwner;

    @Column (name ="cc_number")
    private String ccNumber;

    @Column (name ="cc_expires")
    private String ccExpires;

    @Column (name ="cc_cvv")
    private String ccCvv;

    public String getCcOwner() {
        return ccOwner;
    }

    public void setCcOwner(String ccOwner) {
        this.ccOwner = ccOwner;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpires() {
        return ccExpires;
    }

    public void setCcExpires(String ccExpires) {
        this.ccExpires = ccExpires;
    }

    public String getCcCvv() {
        return ccCvv;
    }

    public void setCcCvv(String ccCvv) {
        this.ccCvv = ccCvv;
    }

    public void setCardType(CreditCardType cardType) {
        this.cardType = cardType;
    }

    public CreditCardType getCardType() {
        return cardType;
    }
}
