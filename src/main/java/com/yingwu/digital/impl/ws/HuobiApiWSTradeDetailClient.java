package com.yingwu.digital.impl.ws;


import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.POJO.TradeDetail;
import com.yingwu.digital.bean.dto.Tick;
import com.yingwu.digital.bean.dto.TradeDataDto;
import com.yingwu.digital.domain.resp.HuobiWSTradeDetailResp;
import com.yingwu.digital.domain.ws.HuobiWSSub;
import com.yingwu.digital.domain.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.impl.HuobiApiWSClientImpl;
import com.yingwu.digital.misc.HuobiWSEventHandler;

import java.util.UUID;

public class HuobiApiWSTradeDetailClient extends AbsHuobiApiWSClient<HuobiWSTradeDetailResp> {

    private final String symbol;


    public HuobiApiWSTradeDetailClient(HuobiApiWSClientImpl client, HuobiWSEventHandler handler, String symbol) {
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
