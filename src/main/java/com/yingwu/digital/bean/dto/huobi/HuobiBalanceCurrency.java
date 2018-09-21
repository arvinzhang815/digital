package com.yingwu.digital.bean.dto.huobi;

import java.math.BigDecimal;

public class HuobiBalanceCurrency {

    public static final String TYPE_TRADE = "trade";

    public static final String TYPE_FROZEN = "frozen";

    private String currency;

    private String type;

    private BigDecimal balance;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public HuobiBalanceCurrency() {
    }
}
