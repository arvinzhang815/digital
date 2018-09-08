package com.yingwu.digital;

import com.yingwu.digital.domain.HuobiTick;
import org.junit.Assert;
import org.junit.Test;

public class HuobiTickTest {
    @Test
    public void test() throws HuobiApiException {

        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();

        HuobiTick tick = client.tick("btcusdt");

        Assert.assertNotNull(tick);

    }

}
