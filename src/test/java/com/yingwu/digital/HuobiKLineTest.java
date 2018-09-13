package com.yingwu.digital;

import com.yingwu.digital.base.DigitalApiException;
import com.yingwu.digital.bean.HuobiKLineData;
import com.yingwu.digital.bean.ws.HuobiWSDepthEvent;
import com.yingwu.digital.bean.ws.HuobiWSError;
import com.yingwu.digital.bean.ws.HuobiWSKLineEvent;
import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import com.yingwu.digital.client.huobi.HuobiApiWSClient;
import com.yingwu.digital.service.HuobiWSEventHandler;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HuobiKLineTest {

    @Test
    public void test() throws DigitalApiException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        List<HuobiKLineData> list = client.kline("btcusdt","5min",10);
        Assert.assertFalse(list.isEmpty());
        for (HuobiKLineData data: list){
            System.out.println(data);
        }
    }

    @Test
    public void wsTest() throws DigitalApiException, InterruptedException {
        HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();
        HuobiApiWSClient client = factory.newWSClient();
        client.depth("btcusdt", "step0", new HuobiWSEventHandler() {
            @Override
            public void handleDepth(HuobiWSDepthEvent event) {
                System.out.println(event.toString());
            }
        });

//        client.kline("ltcbtc", "5min", new HuobiWSEventHandler() {
//            @Override
//            public void handleKLine(HuobiWSKLineEvent event) {
//                System.out.println(event);
//                KLine kLine = new KLine();
//                BeanUtils.copyProperties(event,kLine);
//                HuobiKLineData data = event.getData();
//                BeanUtils.copyProperties(data,kLine);
//                System.out.println(kLine.toString());
//            }
//
//            @Override
//            public void onError(HuobiWSError error) {
//                System.err.println(error);
//            }
//        });
        HuobiApiWSClient client1 = factory.newWSClient();

        client.kline("ltcbtc", "1min", new HuobiWSEventHandler() {
            @Override
            public void handleKLine(HuobiWSKLineEvent event) {
                System.out.println(event);
            }

            @Override
            public void onError(HuobiWSError error) {
                System.err.println(error);
            }
        });

        Thread.sleep(100000000 * 10);
    }
}
