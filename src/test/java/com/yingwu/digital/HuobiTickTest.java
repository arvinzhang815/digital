package com.yingwu.digital;

import com.yingwu.digital.base.DigitalApiException;
import com.yingwu.digital.bean.HuobiTick;
import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import org.junit.Assert;
import org.junit.Test;

public class HuobiTickTest {
    @Test
    public void test() throws DigitalApiException {

        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();

        HuobiTick tick = client.tick("btcusdt");

        Assert.assertNotNull(tick);

    }

}
