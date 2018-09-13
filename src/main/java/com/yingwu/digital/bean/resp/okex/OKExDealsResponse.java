package com.yingwu.digital.bean.resp.okex;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/11
 **/
public class OKExDealsResponse extends OKExBaseResponse {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DealsResponse{" +
                "data='" + data + '\'' +
                '}';
    }
}
