package com.yingwu.digital.bean.resp.okex;

import java.math.BigDecimal;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/11
 **/
public class OKExDealsData {
    private String tradeId;
    private BigDecimal price;
    private BigDecimal vol;
    private String timestamp;
    private String direction;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "DealsData{" +
                "tradeId='" + tradeId + '\'' +
                ", price=" + price +
                ", vol=" + vol +
                ", timestamp='" + timestamp + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
