package com.yingwu.digital.bean.resp.huobi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.bean.HuobiMarketDetail;

@JsonIgnoreProperties
public class HuobiWSMarketDetailResp extends HuobiWSResp {

    public HuobiMarketDetail tick;

    public HuobiWSMarketDetailResp() {

    }
}
