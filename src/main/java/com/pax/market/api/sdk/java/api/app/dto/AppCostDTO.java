package com.pax.market.api.sdk.java.api.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AppCostDTO implements Serializable {
    private static final long serialVersionUID = 8062561372055297472L;

    private Boolean paid;
    private Integer chargeType;
    private BigDecimal price;
    private String text;
    private String currency;
    private Integer freeTrialDay;

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getFreeTrialDay() {
        return freeTrialDay;
    }

    public void setFreeTrialDay(Integer freeTrialDay) {
        this.freeTrialDay = freeTrialDay;
    }
}
