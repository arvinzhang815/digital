package com.yingwu.digital.bean.ws;

import com.yingwu.digital.bean.HuobiKLineData;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class HuobiWSKLineEvent extends HuobiWSEvent {

    private String symbol;

    private String period;

    private HuobiKLineData data;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public HuobiKLineData getData() {
        return data;
    }

    public void setData(HuobiKLineData data) {
        this.data = data;
    }

    public HuobiWSKLineEvent() {
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("symbol",symbol)
                .append("period",period)
                .append("ts", getTs())
                .append("data", getData())
                .toString();
    }
}
