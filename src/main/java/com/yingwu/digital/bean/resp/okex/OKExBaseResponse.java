package com.yingwu.digital.bean.resp.okex;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/11
 **/
public class OKExBaseResponse {
    private String channel;

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
                '}';
    }
}
