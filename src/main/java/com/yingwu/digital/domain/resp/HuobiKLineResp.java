package com.yingwu.digital.domain.resp;


import com.yingwu.digital.domain.HuobiKLineData;

import java.util.List;

public class HuobiKLineResp extends HuobiResp {

    private List<HuobiKLineData> data;

    public List<HuobiKLineData> getData() {
        return data;
    }

    public void setData(List<HuobiKLineData> data) {
        this.data = data;
    }

    public HuobiKLineResp() {
    }
}
