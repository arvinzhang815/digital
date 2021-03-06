package com.yingwu.digital.service.impl;


import com.yingwu.digital.bean.resp.HuobiWSMarketDetailResp;
import com.yingwu.digital.bean.ws.HuobiWSMarketDetailEvent;
import com.yingwu.digital.bean.ws.HuobiWSSub;
import com.yingwu.digital.service.HuobiWSEventHandler;

import java.util.UUID;

public class HuobiApiWSMarketDetailClient extends AbsHuobiApiWSClient<HuobiWSMarketDetailResp> {

    private final String symbol;

    public HuobiApiWSMarketDetailClient(HuobiApiWSClientImpl client, HuobiWSEventHandler handler, String symbol) {
        super(client, handler, HuobiWSMarketDetailResp.class);
        this.symbol = symbol;
    }

    @Override
    protected HuobiWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        return new HuobiWSSub(String.format("market.%s.detail", symbol), id);
    }

    @Override
    protected void doHandler(HuobiWSMarketDetailResp resp) {
        if (this.handler != null && resp != null && resp.tick != null) {
            HuobiWSMarketDetailEvent event = new HuobiWSMarketDetailEvent();
            event.setTs(resp.ts);
            event.setData(resp.tick);
            event.setChannel(resp.getCh());
            event.setSymbol(this.symbol);
            this.handler.handleMarketDetail(event);
        }
    }
}
