package com.yingwu.digital.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.domain.HuobiTradeDetail;

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
