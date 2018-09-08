package com.yingwu.digital.bean.resp;


import com.yingwu.digital.bean.HuobiKLineData;

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
