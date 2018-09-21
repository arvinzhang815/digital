package com.yingwu.digital.bean.dto;

import java.math.BigDecimal;

public class EntrustInfo {
    private Integer id;

    private String tradeTime;

    private BigDecimal tradeAmount;

    private String entrustTime;

    private BigDecimal entrustAmount;

    private String orderId;

    private String entrustState;

    private String symbol;

    private String entrustType;

    private String entrustSource;

    private String finishTime;

    private BigDecimal entrustPrice;

    private BigDecimal tradeAvgPrice;

    private BigDecimal tradeFee;

    private BigDecimal tradeVol;

    private String ext1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime == null ? null : entrustTime.trim();
    }

    public BigDecimal getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(BigDecimal entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getEntrustState() {
        return entrustState;
    }

    public void setEntrustState(String entrustState) {
        this.entrustState = entrustState == null ? null : entrustState.trim();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType == null ? null : entrustType.trim();
    }

    public String getEntrustSource() {
        return entrustSource;
    }

    public void setEntrustSource(String entrustSource) {
        this.entrustSource = entrustSource == null ? null : entrustSource.trim();
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime == null ? null : finishTime.trim();
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getTradeAvgPrice() {
        return tradeAvgPrice;
    }

    public void setTradeAvgPrice(BigDecimal tradeAvgPrice) {
        this.tradeAvgPrice = tradeAvgPrice;
    }

    public BigDecimal getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(BigDecimal tradeFee) {
        this.tradeFee = tradeFee;
    }

    public BigDecimal getTradeVol() {
        return tradeVol;
    }

    public void setTradeVol(BigDecimal tradeVol) {
        this.tradeVol = tradeVol;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }
}