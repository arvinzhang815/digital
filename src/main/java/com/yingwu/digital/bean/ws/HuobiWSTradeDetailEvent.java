package com.yingwu.digital.bean.ws;

import com.yingwu.digital.bean.HuobiTradeDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class HuobiWSTradeDetailEvent extends HuobiWSEvent {

    private String symbol;

    private List<HuobiTradeDetail> details;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<HuobiTradeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<HuobiTradeDetail> details) {
        this.details = details;
    }

    public HuobiWSTradeDetailEvent() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("symbol",symbol)
                .append("ts",getTs())
                .append("details", getDetails())
                .toString();
    }
}
