package com.yingwu.digital.bean.resp.okex;

public class OKExWSSub {

    private String event;

    private String channel;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public OKExWSSub() {
    }

    public OKExWSSub(String event, String channel) {
        this.event = event;
        this.channel = channel;
    }
}
