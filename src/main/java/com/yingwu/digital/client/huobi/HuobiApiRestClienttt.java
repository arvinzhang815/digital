package com.yingwu.digital.client.huobi;


import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.*;

import java.util.List;

public interface HuobiApiRestClienttt {

    List<HuobiKLineData> kline(String symbol, String period, int size) throws DigitalException;

    HuobiTick tick(String symbol) throws DigitalException;

    List<String> currencys() throws DigitalException;

    List<HuobiSymbol> symbols() throws DigitalException;

    HuobiOrderBook depth(String symbol, String type) throws DigitalException;

    List<HuobiAccount> accounts() throws DigitalException;

    HuobiBalance balance(long accountId) throws DigitalException;

    /**
     * @see HuobiAccount#ACCOUNT_TYPE_SPOT
     * @see HuobiAccount#ACCOUNT_TYPE_OTC
     */
    HuobiBalance balance(String type) throws DigitalException;

    /**
     *
     * @param symbol
     * @param price
     * @param amount
     * @param type buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖
     * @return order id from huobi.pro
     * @throws DigitalException
     */
    String sendOrder(String symbol, String price, String amount, HuobiOrderType type) throws DigitalException;

    String cancelOrder(String orderId) throws DigitalException;

    HuobiOrderInfo orderInfo(String orderId) throws DigitalException;

    HuobiOrderMatchResult matchResult(String orderId) throws DigitalException;

    List<HuobiOrderInfo> orders(String symbol,
                                List<HuobiOrderType> types,
                                String startDate,
                                String endDate,
                                List<HuobiOrderState> states,
                                String fromOrderId,
                                int size) throws DigitalException;

}
