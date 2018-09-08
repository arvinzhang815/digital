package com.yingwu.digital.domain.resp;

import com.google.gson.annotations.SerializedName;
import com.yingwu.digital.domain.HuobiOrderInfo;

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
