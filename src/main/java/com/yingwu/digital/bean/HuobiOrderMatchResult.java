package com.yingwu.digital.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;


public class HuobiOrderMatchResult {

    @JSONField(name ="created-at")
    private String entrustTime;

    /**
     * 成交数量
     */
    @JSONField(name ="filled-amount")
    private BigDecimal tradeAmount;

    @JSONField(name ="filled-fees")
    private BigDecimal tradeFee;

    @JSONField(name ="id")
    private String id;

    @JSONField(name ="match-id")
    private String matchId;

    @JSONField(name ="order-id")
    private String orderId;

    @JSONField(name ="price")
    private BigDecimal tradePrice;

    @JSONField(name ="source")
    private String entrustSource;

    @JSONField(name ="symbol")
    private String symbol;

    @JSONField(name ="type")
    private String entrustType;

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(BigDecimal tradeFee) {
        this.tradeFee = tradeFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getEntrustSource() {
        return entrustSource;
    }

    public void setEntrustSource(String entrustSource) {
        this.entrustSource = entrustSource;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public HuobiOrderMatchResult() {
    }

    @Override
    public String toString() {
        return "HuobiOrderMatchResult{" +
                "entrustTime=" + entrustTime +
                ", tradeAmount='" + tradeAmount + '\'' +
                ", tradeFee='" + tradeFee + '\'' +
                ", id=" + id +
                ", matchId=" + matchId +
                ", orderId=" + orderId +
                ", tradePrice='" + tradePrice + '\'' +
                ", entrustSource='" + entrustSource + '\'' +
                ", symbol='" + symbol + '\'' +
                ", entrustType='" + entrustType + '\'' +
                '}';
    }
}
