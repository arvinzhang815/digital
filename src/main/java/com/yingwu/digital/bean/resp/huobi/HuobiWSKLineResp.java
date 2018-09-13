package com.yingwu.digital.bean.resp.huobi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yingwu.digital.bean.HuobiKLineData;

@JsonIgnoreProperties
public class HuobiWSKLineResp extends HuobiWSResp{

    public HuobiKLineData tick;

    public HuobiWSKLineResp() {
    }
}
