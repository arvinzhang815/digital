package com.yingwu.digital;

import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.client.huobi.HuobiApiWSClient;
import com.yingwu.digital.service.HuobiWSEventHandler;
import org.junit.Test;

public class HuobiTradeDetailTest {

    @Test
    public void wsTest() throws DigitalException, InterruptedException {
        ApiClientFactory factory = ApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();

        client.tradeDetail("btcusdt",  new HuobiWSEventHandler() {

            @Override
            public void handleTradeDetail(HuobiWSTradeDetailEvent event) {
                System.out.println(event);
            }
        });

        Thread.sleep(1000 * 30);
    }
}
