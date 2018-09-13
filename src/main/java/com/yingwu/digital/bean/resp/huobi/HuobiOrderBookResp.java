package com.yingwu.digital.bean.resp.huobi;

import java.math.BigDecimal;
import java.util.List;

public class HuobiOrderBookResp extends HuobiResp {

    public static class HuobiOrderBookTick {

        public List<List<BigDecimal>> bids;

        public List<List<BigDecimal>> asks;

    }

    public HuobiOrderBookTick tick;

    public HuobiOrderBookResp() {
    }
}
