package com.yingwu.digital.bean.resp.okex;

import java.math.BigDecimal;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/11
 **/
public class OKExTickerResponse extends OKExBaseResponse{
    private TickerData tickerData;
    private class TickerData{
        private String timestamp;
        private BigDecimal high;//最高价(最近的24小时)
        private BigDecimal vol;//成交量(最近的24小时)
        private BigDecimal last;//最新成交价
        private BigDecimal low;//最低价(最近的24小时)
        private BigDecimal buy;//买一价
        private BigDecimal change;//涨跌幅(最近的24小时)
        private BigDecimal sell;//卖一价
        private BigDecimal dayLow;//日最低
        private BigDecimal dayHigh;//日最高

        public BigDecimal getHigh() {
            return high;
        }

        public void setHigh(BigDecimal high) {
            this.high = high;
        }

        public BigDecimal getVol() {
            return vol;
        }

        public void setVol(BigDecimal vol) {
            this.vol = vol;
        }

        public BigDecimal getLast() {
            return last;
        }

        public void setLast(BigDecimal last) {
            this.last = last;
        }

        public BigDecimal getLow() {
            return low;
        }

        public void setLow(BigDecimal low) {
            this.low = low;
        }

        public BigDecimal getBuy() {
            return buy;
        }

        public void setBuy(BigDecimal buy) {
            this.buy = buy;
        }

        public BigDecimal getChange() {
            return change;
        }

        public void setChange(BigDecimal change) {
            this.change = change;
        }

        public BigDecimal getSell() {
            return sell;
        }

        public void setSell(BigDecimal sell) {
            this.sell = sell;
        }

        public BigDecimal getDayLow() {
            return dayLow;
        }

        public void setDayLow(BigDecimal dayLow) {
            this.dayLow = dayLow;
        }

        public BigDecimal getDayHigh() {
            return dayHigh;
        }

        public void setDayHigh(BigDecimal dayHigh) {
            this.dayHigh = dayHigh;
        }
        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "TickerData{" +
                    "timestamp='" + timestamp + '\'' +
                    ", high=" + high +
                    ", vol=" + vol +
                    ", last=" + last +
                    ", low=" + low +
                    ", buy=" + buy +
                    ", change=" + change +
                    ", sell=" + sell +
                    ", dayLow=" + dayLow +
                    ", dayHigh=" + dayHigh +
                    '}';
        }
    }

    public TickerData getTickerData() {
        return tickerData;
    }

    public void setTickerData(TickerData tickerData) {
        this.tickerData = tickerData;
    }

    @Override
    public String toString() {
        return "TickerResponse{" +
                "data=" + tickerData.toString() +
                '}';
    }
}
