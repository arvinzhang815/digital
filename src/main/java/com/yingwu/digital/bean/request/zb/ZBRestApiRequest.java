package com.yingwu.digital.bean.request.zb;

import com.yingwu.digital.bean.request.ApiRequest;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/27
 **/
public class ZBRestApiRequest extends ApiRequest {
    private String symbols;//交易对
    private String size;//档位1-50，如果有合并深度，只能返回5档深度
    private String merge;//默认深度
    private String orderId;//从指定交易ID后50条数据，指定id
    private String times;//k线周期

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "ZBRestApiRequest{" +
                "symbols='" + symbols + '\'' +
                ", size=" + size +
                ", merge='" + merge + '\'' +
                ", orderId='" + orderId + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
