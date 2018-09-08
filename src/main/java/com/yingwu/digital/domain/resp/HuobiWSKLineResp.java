package com.yingwu.digital.domain.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.domain.HuobiKLineData;

@JsonIgnoreProperties
public class HuobiWSKLineResp extends HuobiWSResp{

    public HuobiKLineData tick;

    public HuobiWSKLineResp() {
    }
}
