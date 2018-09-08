package com.yingwu.digital.domain.resp;

import com.google.gson.annotations.SerializedName;
import com.yingwu.digital.domain.HuobiOrderInfo;

import java.util.List;

/**
 * Created by dev on 1/8/18.
 */
public class HuobiOrdersResp extends HuobiResp {

    @SerializedName("data")
    private List<HuobiOrderInfo> orderInfos;

    public List<HuobiOrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<HuobiOrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public HuobiOrdersResp() {
    }
}
