package com.yingwu.digital.bean.resp.okex;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/11
 **/
public class OKExBaseResponse {
    private String channel;

    public String result;

    public String errorcode;

    public String data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "OKExBaseResponse{" +
                "channel='" + channel + '\'' +
                ", result='" + result + '\'' +
                ", errorcode='" + errorcode + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
