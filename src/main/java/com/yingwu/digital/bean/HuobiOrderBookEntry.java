package com.yingwu.digital.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@JsonDeserialize(using = HuobiOrderBookEntryDeserializer.class)
public class HuobiOrderBookEntry {

    private BigDecimal price;

    private BigDecimal qty;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public HuobiOrderBookEntry() {
    }

    public HuobiOrderBookEntry(BigDecimal price, BigDecimal qty) {
        this.price = price;
        this.qty = qty;
    }

    public static HuobiOrderBookEntry parseOne(List<BigDecimal> list){
        return new HuobiOrderBookEntry(list.get(0),list.get(1));
    }

    public static List<HuobiOrderBookEntry> parseMany(List<List<BigDecimal>> list){
        return list.stream().map( (e)-> parseOne(e)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", String.valueOf(price), String.valueOf(qty));
    }
}
