package com.yingwu.digital.bean.resp.huobi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.bean.HuobiTradeDetail;

import java.util.List;

@JsonIgnoreProperties
public class HuobiWSTradeDetailResp extends HuobiWSResp {

    public static class HuobiWSTradeDetailTick {

        public long id;

        public String ts;

        public List<HuobiTradeDetail> data;

        public HuobiWSTradeDetailTick() {
        }
    }

    public HuobiWSTradeDetailTick tick;

    public HuobiWSTradeDetailResp() {
    }
}
