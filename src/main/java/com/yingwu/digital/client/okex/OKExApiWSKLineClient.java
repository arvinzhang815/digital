package com.yingwu.digital.client.okex;

import com.yingwu.digital.bean.resp.huobi.HuobiWSKLineResp;
import com.yingwu.digital.bean.resp.okex.OKExKLineResponse;
import com.yingwu.digital.bean.resp.okex.OKExWSSub;
import com.yingwu.digital.bean.ws.HuobiWSKLineEvent;
import com.yingwu.digital.bean.ws.HuobiWSSub;
import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;
import com.yingwu.digital.service.HuobiWSEventHandler;
import com.yingwu.digital.service.OKExWSEventHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.UUID;

public class OKExApiWSKLineClient extends AbsOKExApiWSClient<OKExKLineResponse> {

    private static String[] VALID_PERIODS = new String[]{"1min", "5min", "15min", "30min", "60min", "1day", "1mon", "1week", "1year"};

    private final String symbol;

    private final String period;

    public OKExApiWSKLineClient(OKExApiWSClientImpl client, OKExWSEventHandler handler, String symbol, String period) {
        super(client, handler, OKExKLineResponse.class);
        if (StringUtils.isEmpty(symbol) || StringUtils.isEmpty(period) || handler == null) {
            throw new IllegalArgumentException("symbol|period|handler not valid");
        }
        if (Arrays.stream(VALID_PERIODS).noneMatch((e) -> e.equals(period))) {
            throw new IllegalArgumentException("type is not valid.");
        }
        this.symbol = symbol;
        this.period = period;
    }

    @Override
    protected OKExWSSub calcSub() {
        String id = UUID.randomUUID().toString();
        OKExWSSub sub = new OKExWSSub("addChannel",String.format("ok_sub_spot_%s_kline_%s", symbol,period));
        return sub;
    }

    @Override
    protected void doHandler(OKExKLineResponse resp) {
        if (this.handler != null && resp != null ) {
//            HuobiWSKLineEvent event = new HuobiWSKLineEvent();
//            event.setSymbol(symbol);
//            event.setPeriod(period);
//            event.setChannel(resp.getCh());
//            event.setTs(resp.ts);
//            event.setData(resp.tick);
            this.handler.handleKLine(resp);
        }
    }
}
