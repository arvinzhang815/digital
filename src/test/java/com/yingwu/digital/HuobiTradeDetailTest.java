package com.yingwu.digital;

import com.yingwu.digital.domain.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.misc.HuobiWSEventHandler;
import org.junit.Test;

public class HuobiTradeDetailTest {

    @Test
    public void wsTest() throws HuobiApiException, InterruptedException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
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
