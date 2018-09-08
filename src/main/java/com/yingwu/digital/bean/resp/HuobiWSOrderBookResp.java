package com.yingwu.digital.bean.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.bean.HuobiOrderBookEntry;

import java.util.List;

@JsonIgnoreProperties
public class HuobiWSOrderBookResp extends HuobiWSResp {

    @JsonIgnoreProperties
    public static class HuobiOrderBookTick {

        public List<HuobiOrderBookEntry> bids;

        public List<HuobiOrderBookEntry> asks;

        public long ts;

        public long version;

    }

    public HuobiOrderBookTick tick;

    public HuobiWSOrderBookResp() {

    }

}
