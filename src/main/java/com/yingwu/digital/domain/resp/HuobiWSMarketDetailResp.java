package com.yingwu.digital.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.domain.HuobiMarketDetail;

@JsonIgnoreProperties
public class HuobiWSMarketDetailResp extends HuobiWSResp {

    public HuobiMarketDetail tick;

    public HuobiWSMarketDetailResp() {

    }
}
