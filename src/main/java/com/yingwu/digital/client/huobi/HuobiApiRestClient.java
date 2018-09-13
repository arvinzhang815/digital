package com.yingwu.digital.client.huobi;


import com.yingwu.digital.base.DigitalApiException;
import com.yingwu.digital.bean.*;

import java.util.List;

public interface HuobiApiRestClient {

    List<HuobiKLineData> kline(String symbol, String period, int size) throws DigitalApiException;

    HuobiTick tick(String symbol) throws DigitalApiException;

    List<String> currencys() throws DigitalApiException;

    List<HuobiSymbol> symbols() throws DigitalApiException;

    HuobiOrderBook depth(String symbol, String type) throws DigitalApiException;

    List<HuobiAccount> accounts() throws DigitalApiException;

    HuobiBalance balance(long accountId) throws DigitalApiException;

    /**
     * @see HuobiAccount#ACCOUNT_TYPE_SPOT
     * @see HuobiAccount#ACCOUNT_TYPE_OTC
     */
    HuobiBalance balance(String type) throws DigitalApiException;

    /**
     *
     * @param symbol
     * @param price
     * @param amount
     * @param type buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖
     * @return order id from huobi.pro
     * @throws DigitalApiException
     */
    String sendOrder(String symbol, String price, String amount, HuobiOrderType type) throws DigitalApiException;

    String cancelOrder(String orderId) throws DigitalApiException;

    HuobiOrderInfo orderInfo(String orderId) throws DigitalApiException;

    HuobiOrderMatchResult matchResult(String orderId) throws DigitalApiException;

    List<HuobiOrderInfo> orders(String symbol,
                                List<HuobiOrderType> types,
                                String startDate,
                                String endDate,
                                List<HuobiOrderState> states,
                                String fromOrderId,
                                int size) throws DigitalApiException;

}
