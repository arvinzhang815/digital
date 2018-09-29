package com.yingwu.digital.bean.dto.zb;

import com.alibaba.fastjson.annotation.JSONField;

public class ZBDepth {
    private Integer id;

    @JSONField(name ="timestamp")
    private String ts;

    private String bids;

    private String asks;

    private String ext1;

    private String ext2;

    private String ext3;

    private String symbol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public String getBids() {
        return bids;
    }

    public void setBids(String bids) {
        this.bids = bids == null ? null : bids.trim();
    }

    public String getAsks() {
        return asks;
    }

    public void setAsks(String asks) {
        this.asks = asks == null ? null : asks.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    @Override
    public String toString() {
        return "ZBDepth{" +
                "id=" + id +
                ", ts='" + ts + '\'' +
                ", bids='" + bids + '\'' +
                ", asks='" + asks + '\'' +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}