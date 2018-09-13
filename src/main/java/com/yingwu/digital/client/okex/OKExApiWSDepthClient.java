package com.yingwu.digital.client.okex;

import com.yingwu.digital.bean.resp.huobi.HuobiWSOrderBookResp;
import com.yingwu.digital.bean.resp.okex.OKExDepthResponse;
import com.yingwu.digital.bean.resp.okex.OKExWSSub;
import com.yingwu.digital.bean.ws.HuobiWSDepthEvent;
import com.yingwu.digital.bean.ws.HuobiWSSub;
import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;
import com.yingwu.digital.service.HuobiWSEventHandler;
import com.yingwu.digital.service.OKExWSEventHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.UUID;

public class OKExApiWSDepthClient extends AbsOKExApiWSClient<OKExDepthResponse> {

    private static String[] VALID_TYPES = new String[]{"step0", "step1", "step2", "step3", "step4", "step5"};

    private final String symbol;

    private final String type;

    public OKExApiWSDepthClient(OKExApiWSClientImpl client, OKExWSEventHandler handler, String symbol, String type) {
        super(client, handler, OKExDepthResponse.class);
        if (StringUtils.isEmpty(symbol) || StringUtils.isEmpty(type) || handler == null) {
            throw new IllegalArgumentException("symbol|type|handler not valid");
        }
        if (Arrays.stream(VALID_TYPES).noneMatch((e) -> e.equals(type))) {
            throw new IllegalArgumentException("type is not valid.");
        }
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    protected OKExWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        OKExWSSub sub = new OKExWSSub(String.format("market.%s.depth.%s", symbol, type), id);
        return sub;
    }


    @Override
    protected void doHandler(OKExDepthResponse resp) {
        if (this.handler != null && resp != null && resp.tick != null) {
            OKExDepthResponse event = new OKExDepthResponse();
            event.setSymbol(symbol);
            event.setType(type);
            event.setTs(resp.ts);
            event.setChannel(resp.getCh());
            event.setAsks(resp.tick.asks);
            event.setBids(resp.tick.bids);
            this.handler.handleDepth(event);
        }
    }

}
