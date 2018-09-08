package com.yingwu.digital.bean.resp;


import com.yingwu.digital.bean.HuobiTick;

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
