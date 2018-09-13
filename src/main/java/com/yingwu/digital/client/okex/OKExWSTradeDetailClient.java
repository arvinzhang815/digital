package com.yingwu.digital.client.okex;


import com.yingwu.digital.bean.resp.huobi.HuobiWSTradeDetailResp;
import com.yingwu.digital.bean.ws.HuobiWSSub;
import com.yingwu.digital.bean.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;
import com.yingwu.digital.service.HuobiWSEventHandler;

import java.util.UUID;

public class OKExWSTradeDetailClient extends AbsOKExApiWSClient<HuobiWSTradeDetailResp> {

    private final String symbol;


    public OKExWSTradeDetailClient(HuobiApiWSClientImpl client, HuobiWSEventHandler handler, String symbol) {
        super(client, handler, HuobiWSTradeDetailResp.class);
        this.symbol = symbol;
    }

    @Override
    protected HuobiWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        HuobiWSSub sub = new HuobiWSSub(String.format("market.%s.trade.detail", symbol), id);
        return sub;
    }

    @Override
    protected void doHandler(HuobiWSTradeDetailResp resp) {

        if(resp.tick != null && resp.tick.data != null){
            HuobiWSTradeDetailEvent event = new HuobiWSTradeDetailEvent();
            
            event.setSymbol(this.symbol);
            event.setTs(resp.ts );
            event.setChannel(resp.getCh());
            event.setDetails(resp.tick.data );
            if(this.handler != null){
                this.handler.handleTradeDetail(event);
            }
        }

    }
}
