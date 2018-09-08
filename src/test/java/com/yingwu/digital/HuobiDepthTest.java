package com.yingwu.digital;


import com.yingwu.digital.bean.HuobiOrderBook;
import com.yingwu.digital.bean.ws.HuobiWSDepthEvent;
import com.yingwu.digital.service.HuobiWSEventHandler;
import org.junit.Assert;
import org.junit.Test;

public class HuobiDepthTest {

    @Test
    public void wsTest() throws HuobiApiException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();
        client.depth("btcusdt", "step0", new HuobiWSEventHandler() {
            @Override
            public void handleDepth(HuobiWSDepthEvent event) {
                System.out.println(event.toString());
            }
        });

    }

    @Test
    public void restTest() throws HuobiApiException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        HuobiOrderBook ob = client.depth("eosbtc", "step0");
        Assert.assertNotNull( ob );
        System.out.println(ob);
    }

}
