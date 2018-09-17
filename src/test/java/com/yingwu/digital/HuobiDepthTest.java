package com.yingwu.digital;


import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.HuobiOrderBook;
import com.yingwu.digital.bean.resp.okex.OKExDepthResponse;
import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import com.yingwu.digital.client.okex.OKExApiWSClient;
import com.yingwu.digital.service.OKExWSEventHandler;
import org.junit.Assert;
import org.junit.Test;

public class HuobiDepthTest {

    @Test
    public void wsTest() throws DigitalException {
        ApiClientFactory factory = ApiClientFactory.newInstance();
        OKExApiWSClient client = factory.newOKExWSClient();
        client.depth("bch_btc", "5", new OKExWSEventHandler() {
            @Override
            public void handleDepth(OKExDepthResponse event) {
                System.out.println(event.toString());
            }
        });

    }

    @Test
    public void restTest() throws DigitalException {
        ApiClientFactory factory = ApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
//        HuobiOrderBook ob = client.depth("eosbtc", "step0");
//        Assert.assertNotNull( ob );
//        System.out.println(ob);
    }

}
