package com.yingwu.digital.bean.request.huobi;

import com.yingwu.digital.bean.request.ApiRequest;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/19
 **/
public class HuobiOrderInfoRequest extends ApiRequest {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "HuobiOrderInfoRequest{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
