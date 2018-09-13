package com.yingwu.digital.bean.resp.huobi;

import com.google.gson.annotations.SerializedName;
import com.yingwu.digital.bean.HuobiOrderInfo;

public class HuobiOrderInfolResp extends HuobiResp {

    @SerializedName("data")
    private HuobiOrderInfo orderDetail;

    public HuobiOrderInfo getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(HuobiOrderInfo orderDetail) {
        this.orderDetail = orderDetail;
    }

    public HuobiOrderInfolResp() {
    }
}
