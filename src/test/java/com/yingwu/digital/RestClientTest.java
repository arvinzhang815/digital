package com.yingwu.digital;

import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.*;
import com.yingwu.digital.bean.resp.huobi.CurrencysResponse;
import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import com.yingwu.digital.util.ApiUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClientTest {


    private static final String HUOBI_ACCESS_KEY = "";

    private static final String HUOBI_SECRET_KEY = "";

    private static final String HUOBI_PRIVATE_KEY = "";


    @Test
    public void currencysTest() {
        ApiClientFactory factory = ApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        List<String> symbols = null;
        CurrencysResponse response = new CurrencysResponse();
        try {
            response = client.currencys();
            System.out.println(response.toString());
        } catch (DigitalException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        for (String s: symbols){
            System.out.println(s);
        }
        Assert.assertTrue(symbols.contains("btc"));
        Assert.assertFalse(symbols.isEmpty());
    }

    @Test
    public void symbolsTest(){

        ApiClientFactory factory = ApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        List<HuobiSymbol> symbols = new ArrayList<>();
        Map<String, HuobiSymbol> map = new HashMap<>();
        try {
//            symbols = client.symbols();
//
//            for (HuobiSymbol s: symbols){
//                map.put( s.getSymbol(),s);
//                System.out.println(s);
//            }

        } catch (DigitalException e) {
            e.printStackTrace();
        }
        Assert.assertFalse(symbols.isEmpty());
        Assert.assertTrue(map.containsKey("manabtc"));
    }


    @Test
    public void depthTest() {
        ApiClientFactory factory = ApiClientFactory.newInstance();
        HuobiApiRestClient client = factory.newRestClient();
        HuobiOrderBook orderBook = null;
//        try {
//            orderBook = client.depth("manabtc", "step0");
//        } catch (DigitalException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
        System.out.println(orderBook);
        Assert.assertFalse(orderBook.getAsks().isEmpty());
    }

    // @Test
    public void accountsTest() throws DigitalException {
        ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
        HuobiApiRestClient client = factory.newRestClient();
//        List<HuobiAccount> accounts = client.accounts();
//        Assert.assertFalse(accounts.isEmpty());
        // logger.info("accounts:{}", HuobiUtil.toJson(accounts));
    }

    // @Test
    public void balanceTest() throws DigitalException {
        ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
        HuobiApiRestClient client = factory.newRestClient();
//        HuobiBalance ret = client.balance(HuobiAccount.ACCOUNT_TYPE_SPOT);
//        // logger.info("balance:{}", HuobiUtil.toJson(ret));
//        Assert.assertFalse( ret.getCurrencies().isEmpty());
    }

    // @Test
    public void balanceTest2() throws DigitalException {
        ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
        HuobiApiRestClient client = factory.newRestClient();
//        HuobiBalance ret = client.balance(964900L);
//        Assert.assertFalse( ret.getCurrencies().isEmpty());
    }

    /**
     * 641177079 & 641275426
     *
     * @throws DigitalException
     */
    // @Test
    public void sendOrderTest() {
        try {
            ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
            HuobiApiRestClient client = factory.newRestClient();
//            String orderId = client.sendOrder("btcusdt", "1", "0.001", HuobiOrderType.BUY_LIMIT);
//            // logger.info("order success id : {}", orderId);
//
//            client.cancelOrder(orderId);
            // logger.info("cancel order success id : {}", orderId);

        } catch (DigitalException e) {
            // logger.error(e.getMessage());
        }
    }
    // @Test
    public void cancelOrderTest() throws DigitalException {
        ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
        HuobiApiRestClient client = factory.newRestClient();
//        String orderId = client.cancelOrder("641275426");
    }

    // @Test
    public void orderInfoTest() {

        try {
            ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
            HuobiApiRestClient client = factory.newRestClient();
//            HuobiOrderInfo orderDetail = client.orderInfo("641177079");
            // logger.info("order detail:{}", HuobiUtil.toJson(orderDetail));
        } catch (DigitalException e) {
            // logger.error(e.getMessage());
        }
    }

    // @Test
    public void matchResultTest() {
        try {
            ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
            HuobiApiRestClient client = factory.newRestClient();
//            HuobiOrderMatchResult matchResult = client.matchResult("641177079");
            // logger.info("matchResult:{}", HuobiUtil.toJson(matchResult));
        } catch (DigitalException e) {
            // logger.error(e.getMessage());
        }

    }

    // @Test
    public void ordresTest() {
        try {
            ApiClientFactory factory = ApiClientFactory.newInstance(HUOBI_ACCESS_KEY, HUOBI_SECRET_KEY,HUOBI_PRIVATE_KEY);
            HuobiApiRestClient client = factory.newRestClient();
            List<HuobiOrderType> types = new ArrayList<>();
            List<HuobiOrderState> states = new ArrayList<>();
            states.add( HuobiOrderState.CANCELED);
//            List<HuobiOrderInfo> infos = client.orders("btcusdt", types,null,null,   states, null,0);
//            System.out.println(ApiUtil.toJson(infos));
            // logger.info("ordres:{}", HuobiUtil.toJson(infos));
        } catch (DigitalException e) {
            // logger.error(e.getMessage());
        }
    }



}
