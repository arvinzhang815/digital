package com.yingwu.digital.client.okex;


import com.yingwu.digital.bean.resp.huobi.HuobiWSTradeDetailResp;
import com.yingwu.digital.bean.resp.okex.OKExDealsResponse;
import com.yingwu.digital.bean.resp.okex.OKExWSSub;
import com.yingwu.digital.bean.ws.HuobiWSSub;
import com.yingwu.digital.bean.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;
import com.yingwu.digital.service.HuobiWSEventHandler;
import com.yingwu.digital.service.OKExWSEventHandler;

import java.util.UUID;

public class OKExWSDealsClient extends AbsOKExApiWSClient<OKExDealsResponse> {

    private final String symbol;


    public OKExWSDealsClient(OKExApiWSClientImpl client, OKExWSEventHandler handler, String symbol) {
        super(client, handler, OKExDealsResponse.class);
        this.symbol = symbol;
    }

    @Override
    protected OKExWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        OKExWSSub sub = new OKExWSSub("addChannel",String.format("ok_sub_spot_%s_deals", symbol));
        return sub;
    }

    @Override
    protected void doHandler(OKExDealsResponse resp) {

        if(resp != null && resp.getData() != null){
//            HuobiWSTradeDetailEvent event = new HuobiWSTradeDetailEvent();
//
//            event.setSymbol(this.symbol);
//            event.setTs(resp.ts );
//            event.setChannel(resp.getCh());
//            event.setDetails(resp.tick.data );
            if(this.handler != null){
                this.handler.handleDeals(resp);
            }
        }

    }
}
