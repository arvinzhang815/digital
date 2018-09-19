package com.yingwu.digital.bean.dto.huobi;

import java.math.BigDecimal;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/17
 **/
public class Symbol {
    private String baseCurrency;//基础币种
    private String quoteCurrency;//计价币种
    private String symbol;
    private String pricePrecision;//价格精度位数
    private String amountPrecision;//数量精度位数
    private String symbolPrecision;//交易区

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(String pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public String getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision(String amountPrecision) {
        this.amountPrecision = amountPrecision;
    }

    public String getSymbolPrecision() {
        return symbolPrecision;
    }

    public void setSymbolPrecision(String symbolPrecision) {
        this.symbolPrecision = symbolPrecision;
    }
}
