package com.yingwu.digital.domain.resp;


import com.yingwu.digital.domain.HuobiTick;

public class HuobiTickResp extends HuobiResp {

    private HuobiTick tick;

    public HuobiTick getTick() {
        return tick;
    }

    public void setTick(HuobiTick tick) {
        this.tick = tick;
    }

    public HuobiTickResp() {
    }
}
