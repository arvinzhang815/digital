package com.yingwu.digital.bean.resp.huobi;

import com.google.gson.annotations.SerializedName;


public class HuobiOrderResp extends HuobiResp{

    @SerializedName("data")
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public HuobiOrderResp() {
    }
}
