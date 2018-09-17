package com.yingwu.digital.client.okex;


import com.yingwu.digital.bean.resp.huobi.HuobiWSMarketDetailResp;
import com.yingwu.digital.bean.resp.okex.OKExTickerResponse;
import com.yingwu.digital.bean.resp.okex.OKExWSSub;
import com.yingwu.digital.bean.ws.HuobiWSMarketDetailEvent;
import com.yingwu.digital.bean.ws.HuobiWSSub;
import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;
import com.yingwu.digital.service.HuobiWSEventHandler;
import com.yingwu.digital.service.OKExWSEventHandler;

import java.util.UUID;

public class OKExApiWSTickerClient extends AbsOKExApiWSClient<OKExTickerResponse> {

    private final String symbol;

    public OKExApiWSTickerClient(OKExApiWSClientImpl client, OKExWSEventHandler handler, String symbol) {
        super(client, handler, OKExTickerResponse.class);
        this.symbol = symbol;
    }

    @Override
    protected OKExWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        return new OKExWSSub("addChannel",String.format("ok_sub_spot_%s_ticker", symbol));
    }

    @Override
    protected void doHandler(OKExTickerResponse resp) {
        if (this.handler != null && resp != null && resp.getTickerData() != null) {
//            HuobiWSMarketDetailEvent event = new HuobiWSMarketDetailEvent();
//            event.setTs(resp.ts);
//            event.setData(resp.tick);
//            event.setChannel(resp.getCh());
//            event.setSymbol(this.symbol);
            this.handler.handleTicker(resp);
        }
    }
}
