package com.yingwu.digital.domain.ws;

public class HuobiWSEvent {

    private String channel;
    private String ts;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public HuobiWSEvent() {
    }
}
