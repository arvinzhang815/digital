package com.yingwu.digital.bean;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

public class HuobiTick {

    private long id;

    private long ts;

    private BigDecimal close;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal amount;

    private int count;

    private BigDecimal vol;

    /**
     * [price,qty]
     */
    @SerializedName("ask")
    private ArrayList<BigDecimal> asks;

    /**
     * [price,qty]
     */
    @SerializedName("bid")
    private ArrayList<BigDecimal> bids;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public ArrayList<BigDecimal> getAsks() {
        return asks;
    }

    public void setAsks(ArrayList<BigDecimal> asks) {
        this.asks = asks;
    }

    public ArrayList<BigDecimal> getBids() {
        return bids;
    }

    public void setBids(ArrayList<BigDecimal> bids) {
        this.bids = bids;
    }

    public HuobiTick() {
    }
}
